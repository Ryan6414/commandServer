package org.mudules.cmd.nbrlsb.utilTools.common;

public class UpdateSb {
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


    public static String user(String pin, String idNumber, String name,
                              String nation, String sex,
                              String hj, String card, String phone,
                              String personnel_type,
                              String tp ,String photo) {


        return "{ \n" +
                "    \"funcId\": \"cmd.update.user\", \n" +
                "    \"payload\": { \n" +
                "        \"params\": { \n" +
                "            \"users\": { \n" +
                "                \"pin\": \"" + pin + "\", \n" +
                "                \"identity_number\": \"" + idNumber + "\", \n" +
                "                \"name\": \"" + name + "\", \n" +
                "                \"nation\": \"" + nation + "\", \n" +
                "                \"sex\": \"" + sex + "\", \n" +
                "                \"birth\": \"\", \n" +
                "                \"address\": \"" + hj + "\", \n" +
                "                \"card\": \"" + card + "\",\n" +
                "                \"phone\": \"" + phone + "\",\n" +
                "                \"identity_photo\": \"" + tp + "\", \n" +
                "                \"photo\": \""+photo+"\", \n" +
                "                \"personnel_type\": \"" + personnel_type + "\", \n" +
                "                \"fingperprint\": [{ \n" +
                "                    \"index\": \"0\", \n" +
                "                    \"template\": \"xxxxxxxxxx\" \n" +
                "                }] \n" +
                "            } \n" +
                "        } \n" +
                "    } \n" +
                "} ";
    }

    /**
     * @param idNumber       身份证
     * @param name           名字
     * @param sex            性别
     * @param nation         民族
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param personnel_type 人员类别:1干警 ,2外来人员, 3驾驶员
     * @param tp             图片
     * @return JSON字符串
     */
    public static String whitelist(String idNumber, String name, String sex, String nation,
                                   String startTime, String endTime, String personnel_type, String tp) {
        String fSex = "";
        if ("男".equals(sex)) {
            fSex = "1";
        } else if ("女".equals(sex)) {
            fSex = "2";
        } else {
            fSex = "0";
        }


        return "{\n" +
                "    \"funcId\": \"cmd.update.whitelist\", \n" +
                "    \"payload\": {\n" +
                "        \"params\": {\n" +
                "            \"users\": {\n" +
                "                \"identity_number\": \"" + idNumber + "\", \n" +
                "                \"name\": \"" + name + "\",\n" +
                "                \"nation\": \"" + nation + "\",\n" +
                "                \"sex\": \"" + sex + "\",\n" +
                "                \"card\": \"xxxxxxx\",\n" +
                "                \"photo\": \"" + tp + "\",\n" +
                "                \"start_time\": \"" + startTime + "\",\n" +
                "                \"end_time\": \"" + endTime + "\",\n" +
                "                \"check_num\": \"-1\"\n" +
                "                \"personnel_type\": \"" + personnel_type + "\", \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }


    public static String delUser(String idCardNumber) {
        return "{ \n" +
                "    \"funcId\": \"cmd.delete.user\", \n" +
                "    \"payload\": { \n" +
                "        \"params\": { \n" +
                "            \"pin\": [\"" + idCardNumber + "\"] \n" +
                "        } \n" +
                "    } \n" +
                "} ";
    }

    public static String delWhitelist(String idCarNumber) {
        return "{\n" +
                "    \"funcId\": \"cmd.delete.whitelist\", \n" +
                "    \"payload\": {\n" +
                "        \"params\": {\n" +
                "            \"identity_numbers\": [\"" + idCarNumber + "\"]\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

}
