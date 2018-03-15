package com.smart.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;

/**
 * MD5 encrypt
 */
public class Md5Util {

    private static final Logger logger = Logger.getLogger(Md5Util.class);

    /**
     * get md5 encrypt string
     * @param source
     * @return
     */
    public static String md5(String source) {
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            logger.error("Can not encode the string '" + source + "' to MD5!", e);
            return null;
        }
        return sb.toString();
    }

}
