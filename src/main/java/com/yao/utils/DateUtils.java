package com.yao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }
    public static String timeToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        return sf.format(date);
    }

    public static String dateTimeToStr(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }
}
