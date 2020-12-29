package com.xdevs.shopnow.enduser.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFormatUtils {
    public static void debug(String requestId, String message, Throwable ex){
        if(log.isDebugEnabled()){
            log.debug(String.format("[REQUEST-ID]: %s => %s", requestId, message), ex);
        }
    }

    public static void info(String requestId, String message, Throwable ex){
        if(log.isInfoEnabled()){
            log.info(String.format("[REQUEST-ID]: %s => %s", requestId, message), ex);
        }
    }

    public static void warn(String requestId, String message, Throwable ex){
        if(log.isWarnEnabled()){
            log.warn(String.format("[REQUEST-ID]: %s => %s", requestId, message), ex);
        }
    }

    public static void error(String requestId, String message, Throwable ex){
        if(log.isErrorEnabled()){
            log.error(String.format("[REQUEST-ID]: %s => %s", requestId, message), ex);
        }
    }

    public static void debug(String requestId, String message){
       debug(requestId, message, null);
    }

    public static void info(String requestId, String message){
       info(requestId, message, null);
    }

    public static void warn(String requestId, String message){
        warn(requestId, message, null);
    }

    public static void error(String requestId, String message){
        error(requestId, message, null);
    }

}
