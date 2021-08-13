package org.mudules.cmd.nbrlsb.utilTools.common;

import com.alibaba.fastjson.JSONObject;

public class JSONUtils {

    public static String getCmd(String cmd,String funcId){
        JSONObject json = JSONObject.parseObject(cmd);
        return  json.getString(funcId);
    }


}
