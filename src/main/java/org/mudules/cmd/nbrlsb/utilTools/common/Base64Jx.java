package org.mudules.cmd.nbrlsb.utilTools.common;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Base64Jx {

    public static String getBase64Code(String imgUrl){
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgUrl);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ""; //如果数据库照片路径有问题直接返回空.
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串

        //base64编码
        return encoder.encode(Objects.requireNonNull(data));
    }
}
