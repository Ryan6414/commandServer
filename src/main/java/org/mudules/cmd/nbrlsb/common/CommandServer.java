package org.mudules.cmd.nbrlsb.common;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import org.mudules.cmd.nbrlsb.NbOutsider.entity.NbOutsider;
import org.mudules.cmd.nbrlsb.NbOutsider.service.INbOutsiderService;
import org.mudules.cmd.nbrlsb.snStatus.server.SnStatusServer;
import org.mudules.cmd.nbrlsb.utilTools.common.Base64Jx;
import org.mudules.cmd.nbrlsb.utilTools.common.JwtHttpUtil;
import org.mudules.cmd.nbrlsb.utilTools.common.UpdateSb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class CommandServer {

    private static final String RES_CODE = "{ \n" +
            "    \"code\": 0 ,\n" +
            "    \"message\": \"xxxx\" \n" +
            "} ";

    private static final String RY = "user";       // 人员上传
    private static final String BMD = "whitelist";  // 白名单上传
    private static final String HMD = "blacklist";  //  黑名单上传
    private static final String HYJL = "checklog"; //  核验记录上传
    private static final String FK = "visitlog"; // 访客记录上传
    private static final String JKJC = "healthlog"; // 健康核查记录上传
    private static final String START = "command"; //开始下发指令
    private static final String END = "finish";   // 设备结束上传.


    @Value("${listener.cmdPort}")
    Integer cmdPort;

    @Value("${listener.imgUrl}")
    String imgUrl;

    ServerSocket server;


    @Autowired
    private INbOutsiderService iNbOutsiderService;

    @Autowired
    private SnStatusServer statusServer;

    @PostConstruct
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
            log.info("命令服务器已开启>>>");
            while (true) {
                BufferedInputStream mInput = null;
                BufferedOutputStream mOutInput = null;
                try {
                    Socket socket = server.accept();
                    if (!socket.isClosed()) {
                        mInput = new BufferedInputStream(socket.getInputStream());
                        mOutInput = new BufferedOutputStream(socket.getOutputStream());
                        byte[] buf = new byte[1024 * 8];
                        int read = mInput.read(buf);

                        String handle = "";
                        // 命令服务器收到任何指令就触发执行
                        if (read > 0 && read < 1024) {
                            // 收到设备指令.
                            log.info("命令服务器收到指令:{}", new String(buf, 0, read));
                            String cmd = new String(buf, 0, read, StandardCharsets.UTF_8);
                            // 处理收到的设备指令:
                            handle = handleCmd(cmd);
                            // 响应指令&下发人员到设备
                            mOutInput.write(handle.getBytes(), 0, handle.getBytes().length);

                        } else {
                            // 超过1K是有base64上传[不完整json字符串],直接响应{code:0 , message:"XXX"}
                            mOutInput.write(RES_CODE.getBytes(), 0, RES_CODE.getBytes().length);
                        }

                    } else {
                        log.info("命令服务器监听已关闭...");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert mOutInput != null;
                        mOutInput.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mOutInput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mInput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
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
        JSONObject json = JSONObject.parseObject(cmd);
        String cd = json.getString("funcId");
        String sb_cmd = cd.split("\\.")[2];
        JSONObject params = json.getJSONObject("payload");
        Integer code = json.getInteger("code");

        String s = params.toString();
        log.info("*******************{}**********************",s);

        if (RY.equals(sb_cmd)) {
            log.info("设备添加新人员");
            return RES_CODE;
        } else if (BMD.equals(sb_cmd)) {
            log.info("白名单");
            JwtHttpUtil.saveUser("http://192.168.128.96:8088/jeecg-boot/rlsbReturnData/handleReturnData", s);
            return RES_CODE;
        } else if (HYJL.equals(sb_cmd)) {
            log.info("核验记录");
            JwtHttpUtil.saveUser("http://192.168.128.96:8088/jeecg-boot/rlsbReturnData/handleReturnData", s);
            return RES_CODE;
        } else if (FK.equals(sb_cmd)) {
            log.info("访客记录");
            return RES_CODE;
        } else if (END.equals(sb_cmd)) {
            log.info("设备记录全部上传完成!");
            return RES_CODE;
        } else if (START.equals(sb_cmd)) { // 设备开启下发命令!
            JSONObject js = JSONObject.parseObject(cmd);
            // 获取需要下发的设备sn号
            String sn = js.getJSONObject("payload").getJSONObject("params").getString("sn");
            // 查询数据库中此设备是否已经下发人员.0准备下发,1已经下发.
            Integer snStatus = statusServer.getSnStatus(sn);

            if (snStatus == 0) {
                return getLoad();  // 全部下发完,有当前方法将设备表中对应设备的状态变为1
            }

        } else if (code == 0) { //前一个上传成功.
            return getLoad();
        } else {
            log.info("其他");
            return RES_CODE;
        }
        return RES_CODE;
    }

    public String getLoad() {
        // 获取人员信息
        NbOutsider nb = iNbOutsiderService.getOneInfo();
        String tp = Base64Jx.getBase64Code(imgUrl + nb.getPhoto());
        if (nb != null && nb.getUpload() == 0) {

            // 获取数据库人员后,马上将这个人员的id设置为1,表示此人在设备1已经下发.
            iNbOutsiderService.updateByUpLoad(1, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);
        } else if (nb != null && nb.getUpload() == 1) {
            iNbOutsiderService.updateByUpLoad(2, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);

        } else if (nb != null && nb.getUpload() == 2) {
            iNbOutsiderService.updateByUpLoad(3, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);

        } else if (nb != null && nb.getUpload() == 3) {
            iNbOutsiderService.updateByUpLoad(4, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);

        } else if (nb != null && nb.getUpload() == 4) {
            iNbOutsiderService.updateByUpLoad(5, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);

        } else if (nb != null && nb.getUpload() == 5) {
            iNbOutsiderService.updateByUpLoad(6, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);

        } else if (nb != null && nb.getUpload() == 6) {
            iNbOutsiderService.updateByUpLoad(7, nb.getId());
            return UpdateSb.user(nb.getIdNumber(), nb.getIdNumber(), nb.getName(), nb.getGender(), nb.getHj(), tp);

        }
        return null;
    }

}
// JwtHttpUtil.saveUser("http://localhost:8845/net/test/b");