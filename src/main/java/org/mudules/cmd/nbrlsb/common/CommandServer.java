package org.mudules.cmd.nbrlsb.common;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;
import org.mudules.cmd.nbrlsb.NbOutsider.service.INbOutsiderService;
import org.mudules.cmd.nbrlsb.NbPoliceInfo.entity.NbPoliceInfo;
import org.mudules.cmd.nbrlsb.NbPoliceInfo.service.INbPoliceInfoService;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.entity.NbFouroffenderInfo;
import org.mudules.cmd.nbrlsb.nbFouroffenderInfo.service.INbFouroffenderInfoService;
import org.mudules.cmd.nbrlsb.nbOffenderInfo.entity.NbOffenderInfo;
import org.mudules.cmd.nbrlsb.nbOffenderInfo.service.INbOffenderInfoService;
import org.mudules.cmd.nbrlsb.snStatus.server.SnStatusServer;
import org.mudules.cmd.nbrlsb.utilTools.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Order(value = 1)
public class CommandServer {

    // 服务器礼貌响应设备
    private static final String RES_CODE = "{ \n" +
            "    \"code\": 0 ,\n" +
            "    \"message\": \"xxxx\" \n" +
            "} ";

    // 服务器下发完成告诉设备已经没有下发内容了,让设备自动断开
    private static final String XFEND = "{ \n" +
            "    \"funcId\": \"cmd.device.finish\"\n" +
            "}";

    private static final String RY = "user";       // 人员上传
    private static final String BMD = "whitelist";  // 白名单上传
    private static final String HMD = "blacklist";  //  黑名单上传
    private static final String HYJL = "checklog"; //  核验记录上传
    private static final String FK = "visitlog"; // 访客记录上传
    private static final String JKJC = "healthlog"; // 健康核查记录上传
    private static final String START = "command"; //开始下发指令
    private static final String END = "finish";   // 设备结束上传.

    @Value("${listener.cmdPort1}")
    Integer cmdPort;

    @Value("${listener.imgUrl}")
    String imgUrl;

   @Value("${listener.requestUrl}")
    String requestUrl; //= "http://192.168.128.96:8088/jeecg-boot/rlsbReturnData/handleReturnData";

    ServerSocket server;

    @Autowired
    private INbOutsiderService iNbOutsiderService;

    @Autowired
    private SnStatusServer statusServer;

    @Autowired
    private INbFouroffenderInfoService iNbFouroffenderInfoService;

    @Autowired
    private INbOffenderInfoService iNbOffenderInfoService;

    @Autowired
    private INbPoliceInfoService iNbPoliceInfoService;

    @PostConstruct
    @Order(value = 1)
    private void qd() {
        start();
    }

    @Async(value = "pool-a")
    public void start() {
        new Thread(() -> {
            try {
                server = new ServerSocket(cmdPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("命令服务器1已开启***");
            while (true) {
                BufferedInputStream mInput = null;
                BufferedOutputStream mOutInput = null;
                try {
                    Socket socket = server.accept();
                    log.info("监听1已启动***");
                    if (!socket.isClosed()) {
                        mInput = new BufferedInputStream(socket.getInputStream());
                        mOutInput = new BufferedOutputStream(socket.getOutputStream());
                        byte[] buf = new byte[1024 * 8];
                        int read = mInput.read(buf);

                        String handle = "";
                        // 命令服务器收到任何指令就触发执行
                        if (read > 0 && read < 1024) {
                            String cmd = new String(buf, 0, read, "gb2312");
                            // 收到设备指令.
                            log.info("命令服务器收到设备*[{}:{}]*指令:{}", socket.getInetAddress().getHostAddress(), socket.getPort(), cmd);
                            if (!"".equals(cmd)) {
                                // 处理收到的设备指令:
                                handle = handleCmd(cmd);
                                // 响应指令&下发人员到设备

                                // 剔除命令字符串中的空格,制表符,换行符 [返回值 0表示请求的设备没有注册, 1表示设
                                handle = StringUtils.replaceBlank(handle);
                                log.info("命令服务器响应设备指令:{}", handle);
                                // 响应设备指令.
                                mOutInput.write(handle.getBytes("gb2312"), 0, handle.getBytes("gb2312").length);
                                mOutInput.flush();
                            }
                        } else {
                            // 超过10K就是有base64上传[不完整json字符串],直接响应{code:0 , message:"XXX"}
                            mOutInput.write(RES_CODE.getBytes(), 0, RES_CODE.getBytes().length);
                            mOutInput.flush();
                        }

                    } else {
                        log.info("命令服务器监听已关闭...");
                        return;
                    }
                } catch (Exception e) {
                    log.info("异常!监听已关闭");
                    e.printStackTrace();
                } finally {
                    try {
                        assert mOutInput != null;
                        mOutInput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.info("写入到设备的流关闭异常!");
                    }
                    try {
                        mInput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.info("设备传来的流关闭异常!");
                    }
                }
            }
        }).start();
    }

    /**
     * @param cmd 设备请求
     * @return 一.设备主动上传: 服务器需要回 {"code" : 0 , "message" : "XXX"}
     * 1_ device.upload.user 人员上传
     * 2_ device.upload.whitelist 白名单上传
     * 3_ device.upload.blacklist 黑名单上传
     * 4_ device.upload.checklog 核验记录上传
     * 5_ device.upload.visitlog 访客记录上传
     * 6_ device.upload.healthlog 健康核查记录上传
     * 7_ device.upload.finish 设备结束上传.
     * 二. 服务器被动下发: 心跳服务器开启command = 1时, 设备会开启下发
     * [ device.upload.command 设备请求命令服务器 服务器准备下发信息, 每次下发成功设备会响应 {"code" : 0 , "message" : "XXX"}]
     * 1_ cmd.update.user 人员下发
     * 2_ cmd.delete.user 删除人员
     * 3_ cmd.delete.biodata 删除模板
     * 4_ cmd.clear.all 清除全部数据
     * 5_ cmd.clear.checklog 清除核验记录
     * 6_ cmd.clear.user 清除人员数据
     * 7_ cmd.device.reboot 重启客户端
     * 8_ cmd.update.blacklist 黑名单下发
     * 9_ cmd.delete.blacklist 删除黑名单
     * ....
     * cmd.device.finish 下发结束,发送后设备断开通讯,并且设备不回复.
     */
    public String handleCmd(String cmd) {
        // 将设备的请求转换成JSON并拆分出来进行分支处理
        // 剥离请求指令device.upload.XXX中的XXX
        String sb_cmd = getRequestCmd(cmd);
        // 剥离请求指令中的 params参数
        String params = getRequestParam(cmd);
        // 剥离设备响应的代码:{"code" : 0 , "message" : "XXX"} -> 0 成功, 其他失败.
        Map<Integer, String> code = getRequestCode(cmd);


        if (RY.equals(sb_cmd)) {     // 设备添加了一个新的人员.
            log.info("设备添加新人员");
            return RES_CODE;
        } else if (BMD.equals(sb_cmd)) { // 设备添加了一个新的白名单
            log.info("设备添加新的白名单");
            return RES_CODE;
        } else if (HYJL.equals(sb_cmd)) {  // 设备做了一次人脸识别,并且识别成功.
            log.info("核验记录");
            JwtHttpUtil.saveUser(requestUrl, params);
            return RES_CODE;
        } else if (FK.equals(sb_cmd)) {   // 设备做了一次访客记录的识别,并且识别成功
            log.info("访客记录");
            JwtHttpUtil.saveUser(requestUrl, params);
            return RES_CODE;
        } else if (END.equals(sb_cmd)) {   // 设备后面的没有需要请求的指令
            log.info("设备记录全部上传完成!");
            return RES_CODE;
        } else if (START.equals(sb_cmd)) { // 设备开启下发命令!服务器准备下发数据到设备中.
            // 获取需要下发的设备sn号
            log.info("下发信息到设备!");
            String sn = getRequestSn(cmd);


            Map<String, Integer> getSnStatusArea = statusServer.getSnStatus(sn);
            // 查询数据库中此设备是否已经下发人员.0准备下发,1已经下发.
            Integer status = getSnStatusArea.get("status");
            // area 1是手持设备[罪犯和干警] 2是[外来人员和干警] 3是[司机和干警]
            Integer area = getSnStatusArea.get("area");

            if (status == 0 || status == 9) {
                return XFEND; // 服务器下发结束.
            }

            if (area == 1) { //罪犯和干警
                NbPoliceInfo onePolice = iNbPoliceInfoService.getOnePolice();
                if (onePolice != null) {
                    String uploadFlag = onePolice.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbPoliceInfoService.updateUploadFlagById("4", onePolice.getId());

                        log.info("上传干警信息到手持设备:{}", sn);

                        /**
                         *
                         * @param pin  警号/身份证
                         * @param idNumber  身份证
                         * @param name  名字
                         * @param nation 民族
                         * @param sex 性别
                         * @param birthday  生日
                         * @param hj  户籍
                         * @param card 卡号/身份证号
                         * @param phone 电话
                         * @param personnel_type 人员类型
                         * @param tp 身份证图片
                         * @param photo 现在的图片
                         * @return json
                         */

                        return UpdateSb.user(
                                onePolice.getPoliceNumber(),
                                onePolice.getIdcardNumber(),
                                onePolice.getName(),
                                onePolice.getNation(),
                                onePolice.getGender(),
                                onePolice.getHjAddress(),
                                onePolice.getPoliceNumber(),
                                onePolice.getPhoneNumber(),
                                "1",  //干警
                                Base64Jx.getBase64Code(imgUrl + onePolice.getPhoto()),
                                ""
                        );
                    } else if ("2".equals(uploadFlag)) {
                        iNbPoliceInfoService.updateUploadFlagById("5", onePolice.getId());
                        return UpdateSb.delUser(onePolice.getIdcardNumber());
                    }
                }
                NbOffenderInfo oneNbOffenderInfo = iNbOffenderInfoService.getOneNbOffenderInfo();
                if (oneNbOffenderInfo != null) {
                    String uploadFlag = oneNbOffenderInfo.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbOffenderInfoService.updateUploadFlagById("4", oneNbOffenderInfo.getId());
                        log.info("上传普通罪犯信息到手持设备:{}", sn);

                        /**
                         *
                         * @param pin  警号/身份证
                         * @param idNumber  身份证
                         * @param name  名字
                         * @param nation 民族
                         * @param sex 性别
                         * @param birthday  生日
                         * @param hj  户籍
                         * @param card 卡号/身份证号
                         * @param phone 电话
                         * @param personnel_type 人员类型
                         * @param tp 身份证图片
                         * @param photo 现在的图片
                         * @return json
                         */
                        return UpdateSb.user(
                                oneNbOffenderInfo.getIdCardNumber(),
                                oneNbOffenderInfo.getIdCardNumber(),
                                oneNbOffenderInfo.getName(),
                                oneNbOffenderInfo.getNation(),
                                oneNbOffenderInfo.getGender(),
                                oneNbOffenderInfo.getHjAddress(),
                                oneNbOffenderInfo.getIdCardNumber(),
                                "",
                                "4", // 普通罪犯
                                Base64Jx.getBase64Code(imgUrl + oneNbOffenderInfo.getPhoto()),
                                ""
                        );
                    } else if ("2".equals(uploadFlag)) {
                        iNbOffenderInfoService.updateUploadFlagById("5", oneNbOffenderInfo.getId());
                        return UpdateSb.delUser(oneNbOffenderInfo.getIdCardNumber());
                    }
                }

                NbFouroffenderInfo oneNbFouroffenderInfo = iNbFouroffenderInfoService.getOneNbFouroffenderInfo();
                if (oneNbFouroffenderInfo != null) {
                    String uploadFlag = oneNbFouroffenderInfo.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbFouroffenderInfoService.updateUploadFlagById("4", oneNbFouroffenderInfo.getId());
                        log.info("上传四监区罪犯信息到手持设备:{}", sn);
                        return UpdateSb.user(
                                oneNbFouroffenderInfo.getIdCardNumber(),
                                oneNbFouroffenderInfo.getIdCardNumber(),
                                oneNbFouroffenderInfo.getName(),
                                oneNbFouroffenderInfo.getNation(),
                                oneNbFouroffenderInfo.getGender(),
                                oneNbFouroffenderInfo.getHjAddress(),
                                oneNbFouroffenderInfo.getIdCardNumber(),
                                "",
                                "5", // 四监区罪犯
                                Base64Jx.getBase64Code(imgUrl + oneNbOffenderInfo.getPhoto()),
                                ""
                        );
                    } else if ("2".equals(uploadFlag)) {
                        iNbFouroffenderInfoService.updateUploadFlagById("5", oneNbOffenderInfo.getId());
                        return UpdateSb.delUser(oneNbFouroffenderInfo.getIdCardNumber());
                    }
                }
                statusServer.updateStatusByStatus(0, 1);

                int i = statusServer.getStatus_9();
                if (i == 0) {
                    iNbPoliceInfoService.updateUploadFlagByFalg("1", "4");
                    iNbPoliceInfoService.updateUploadFlagByFalg("3", "5");
                    iNbOffenderInfoService.updateUploadFlagByFalg("1", "4");
                    iNbOffenderInfoService.updateUploadFlagByFalg("3", "5");
                    iNbFouroffenderInfoService.updateUploadFlagByFalg("1", "4");
                    iNbFouroffenderInfoService.updateUploadFlagByFalg("3", "5");
                } else {
                    iNbPoliceInfoService.updateUploadFlagByFalg("2", "5");
                    iNbPoliceInfoService.updateUploadFlagByFalg("0", "4");
                    iNbOffenderInfoService.updateUploadFlagByFalg("2", "5");
                    iNbOffenderInfoService.updateUploadFlagByFalg("0", "4");
                    iNbFouroffenderInfoService.updateUploadFlagByFalg("2", "5");
                    iNbFouroffenderInfoService.updateUploadFlagByFalg("0", "4");
                    statusServer.updateStatusByStatus(1, 9);
                }
                return XFEND;//告诉设备我GG了

            } else if (area == 2) { //外来人员和干警
                NbPoliceInfo onePolice = iNbPoliceInfoService.getOnePolice();
                if (onePolice != null) {
                    String uploadFlag = onePolice.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbPoliceInfoService.updateUploadFlagById("4", onePolice.getId());
                        log.info("上传干警信息到设备:{} -- 外来人员的设备", sn);
                        return UpdateSb.user(
                                onePolice.getPoliceNumber(),
                                onePolice.getIdcardNumber(),
                                onePolice.getName(),
                                onePolice.getNation(),
                                onePolice.getGender(),
                                onePolice.getHjAddress(),
                                onePolice.getPoliceNumber(),
                                onePolice.getPhoneNumber(),
                                "1",  //干警
                                Base64Jx.getBase64Code(imgUrl + onePolice.getPhoto()),
                                ""
                        );
                    } else if ("2".equals(uploadFlag)) {
                        iNbPoliceInfoService.updateUploadFlagById("5", onePolice.getId());
                        return UpdateSb.delUser(onePolice.getIdcardNumber());
                    }

                }
                NbOutsider oneNboutsider = iNbOutsiderService.getOneNboutsider();
                if (oneNboutsider != null) {
                    String uploadFlag = oneNboutsider.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbOutsiderService.updateUploadFlagById("4", oneNboutsider.getId());
                        /**
                         *
                         * @param idNumber 身份证
                         * @param name 名字
                         * @param sex 性别
                         * @param nation 民族
                         * @param startTime 开始时间
                         * @param endTime 结束时间
                         * @param personnel_type 人员类别:1干警 ,2外来人员, 3驾驶员
                         * @param tp 图片
                         * @return JSON字符串
                         */
                        log.info("上传外来人员信息到设备:{} -- 外来人员的设备", sn);
                        return UpdateSb.whitelist(
                                oneNboutsider.getIdNumber(),
                                oneNboutsider.getName(),
                                oneNboutsider.getGender(),
                                "",//民族
                                DateUtils.DateToStr(oneNboutsider.getStartTime()),
                                DateUtils.DateToStr(oneNboutsider.getEndTime()),
                                "2", //外来人员
                                Base64Jx.getBase64Code(imgUrl + oneNboutsider.getPhoto())
                        );

                    } else if ("2".equals(uploadFlag)) {
                        iNbOutsiderService.updateUploadFlagById("5", oneNboutsider.getId());
                        return UpdateSb.delWhitelist(oneNboutsider.getIdNumber());
                    }

                }
                statusServer.updateStatusByStatus(0, 1);
                int i = statusServer.getStatus_9();
                if (i == 0) {
                    iNbPoliceInfoService.updateUploadFlagByFalg("1", "4");
                    iNbPoliceInfoService.updateUploadFlagByFalg("3", "5");
                    iNbOutsiderService.updateUploadFlagByFalg("1", "4");
                    iNbOutsiderService.updateUploadFlagByFalg("3", "5");
                } else {
                    iNbPoliceInfoService.updateUploadFlagByFalg("2", "5");
                    iNbPoliceInfoService.updateUploadFlagByFalg("0", "4");
                    iNbOutsiderService.updateUploadFlagByFalg("2", "5");
                    iNbOutsiderService.updateUploadFlagByFalg("0", "4");
                    statusServer.updateStatusByStatus(1, 9);
                }
                return XFEND;//告诉设备我GG了
            } else if (area == 3) { // 司机和干警
                NbPoliceInfo onePolice = iNbPoliceInfoService.getOnePolice();
                if (onePolice != null) {
                    String uploadFlag = onePolice.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbPoliceInfoService.updateUploadFlagById("4", onePolice.getId());
                        log.info("上传干警信息到设备:{} -- 外来人员的设备", sn);
                        return UpdateSb.user(
                                onePolice.getPoliceNumber(),
                                onePolice.getIdcardNumber(),
                                onePolice.getName(),
                                onePolice.getNation(),
                                onePolice.getGender(),
                                onePolice.getHjAddress(),
                                onePolice.getPoliceNumber(),
                                onePolice.getPhoneNumber(),
                                "1",  //干警
                                Base64Jx.getBase64Code(imgUrl + onePolice.getPhoto()),
                                ""
                        );
                    } else if ("2".equals(uploadFlag)) {
                        iNbPoliceInfoService.updateUploadFlagById("5", onePolice.getId());
                        return UpdateSb.delUser(onePolice.getIdcardNumber());
                    }
                }
                NbOutsider oneNboutsider = iNbOutsiderService.getOneNboutsider();
                if (oneNboutsider != null) {
                    String uploadFlag = oneNboutsider.getUploadFlag();
                    if ("0".equals(uploadFlag)) {
                        iNbOutsiderService.updateUploadFlagById("4", oneNboutsider.getId());
                        /**
                         *
                         * @param idNumber 身份证
                         * @param name 名字
                         * @param sex 性别
                         * @param nation 民族
                         * @param startTime 开始时间
                         * @param endTime 结束时间
                         * @param personnel_type 人员类别:1干警 ,2外来人员, 3驾驶员
                         * @param tp 图片
                         * @return JSON字符串
                         */
                        log.info("上传驾驶员信息到设备:{} -- 外来人员的设备", sn);
                        return UpdateSb.whitelist(
                                oneNboutsider.getIdNumber(),
                                oneNboutsider.getName(),
                                oneNboutsider.getGender(),
                                "",//民族
                                DateUtils.DateToStr(oneNboutsider.getStartTime()),
                                DateUtils.DateToStr(oneNboutsider.getEndTime()),
                                "3", //驾驶员
                                Base64Jx.getBase64Code(imgUrl + oneNboutsider.getPhoto())
                        );

                    } else if ("2".equals(uploadFlag)) {
                        iNbOutsiderService.updateUploadFlagById("5", oneNboutsider.getId());
                        return UpdateSb.delWhitelist(oneNboutsider.getIdNumber());
                    }
                }
                statusServer.updateStatusByStatus(0, 1);
                int i = statusServer.getStatus_9();
                if (i == 0) {
                    iNbPoliceInfoService.updateUploadFlagByFalg("1", "4");
                    iNbPoliceInfoService.updateUploadFlagByFalg("3", "5");
                    iNbOutsiderService.updateUploadFlagByFalg("1", "4");
                    iNbOutsiderService.updateUploadFlagByFalg("3", "5");
                } else {
                    iNbPoliceInfoService.updateUploadFlagByFalg("2", "5");
                    iNbPoliceInfoService.updateUploadFlagByFalg("0", "4");
                    iNbOutsiderService.updateUploadFlagByFalg("2", "5");
                    iNbOutsiderService.updateUploadFlagByFalg("0", "4");
                    statusServer.updateStatusByStatus(1, 9);
                }
                return XFEND;//告诉设备我GG了
            }

        } else {
            log.info("其他未知命令&命令错误");
            return RES_CODE;
        }
        return RES_CODE;
    }

    private String getRequestSn(String cmd) {
        JSONObject js = JSONObject.parseObject(cmd);
        return js.getJSONObject("payload").getJSONObject("params").getString("sn");
    }

    private Map<Integer,String> getRequestCode(String cmd) {
        JSONObject json = JSONObject.parseObject(cmd);
        Map<Integer ,String> map =new HashMap<>();
        Integer c = json.getInteger("code");
        String m = json.getString("message");
        if (c != null && !"".equals(m) && m != null){
            map.put(c,m);
            return map;
        }
        return null;
    }

    private String getRequestCmd(String cmd) {
        JSONObject json = JSONObject.parseObject(cmd);
        String cd = json.getString("funcId");
        return cd.split("\\.")[2];
    }

    private String getRequestParam(String cmd) {
        JSONObject json = JSONObject.parseObject(cmd);
        JSONObject payload = json.getJSONObject("payload");
        return payload.toString();

    }
}
