package org.mudules.cmd.nbrlsb.utilTools.common;

public class UpdateSb {

    public static String user(String pin, String idNumber, String name, String sex, String hj , String tp) {
        String fSex = "";
        if ("男".equals(sex)) {
            fSex = "1";
        } else if ("女".equals(sex)) {
            fSex = "2";
        } else {
            fSex = "0";
        }


        return "{ \n" +
                "    \"funcId\": \"cmd.update.user\", \n" +
                "    \"payload\": { \n" +
                "        \"params\": { \n" +
                "            \"users\": { \n" +
                "                \"pin\": \"" + pin + "\", \n" +
                "                \"identity_number\": \"" + idNumber + "\", \n" +
                "                \"name\": \"" + name + "\", \n" +
                "                \"nation\": \"xxxxxxx\", \n" +
                "                \"sex\": \"" + fSex + "\", \n" +
                "                \"birth\": \"xxxxxxx\", \n" +
                "                \"address\": \"" + hj + "\", \n" +
                "                \"card\": \"xxxxxxx\",\n" +
                "                \"phone\": \"xxxxxxx\",\n" +
                "                \"identity_photo\": \"xxxxxxx\", \n" +
                "                \"photo\": \"" + tp + "\", \n" +
                "                \"fingperprint\": [{ \n" +
                "                    \"index\": \"0\", \n" +
                "                    \"template\": \"xxxxxxxxxx\" \n" +
                "                }] \n" +
                "            } \n" +
                "        } \n" +
                "    } \n" +
                "} ";
    }


}
