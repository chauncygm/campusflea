package com.smart.utils;

import org.apache.log4j.Logger;

public class Log {

    private static final Logger logger = Logger.getLogger(Log.class);

    public static final String OK = "[OK] ";
    public static final String ERROR = "[ERROR] ";

    //info log
    public static void info(Logger logger, String message, Object... params) {
        logger.info(String.format(message,params));
    }

    //ok info
    public static void ok(Logger logger, String message, Object... params) {
        logger.info(String.format(OK + message, params));
    }

    //fail info
    public static void fail(Logger logger, String message, Object... params) {
        logger.info(String.format(ERROR + message, params));
    }

}
