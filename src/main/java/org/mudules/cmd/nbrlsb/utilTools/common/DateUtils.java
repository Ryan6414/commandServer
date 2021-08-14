package org.mudules.cmd.nbrlsb.utilTools.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String DateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String DateToBirthday(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
