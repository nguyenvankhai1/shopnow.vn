package com.xdevs.shopnow.enduser.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    private static String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String formatDate(Date date, String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatDate(Date date){
        return new SimpleDateFormat(DATETIME_FORMAT).format(date);
    }
}
