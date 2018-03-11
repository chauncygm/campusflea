package com.smart.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Random;

/**
 * Random util
 */
public class RUtil {

    private static final Logger logger = Logger.getLogger(RUtil.class);
    private static Random random = new Random(System.currentTimeMillis());

    private static final String NUMBER_CHARS = "0123456789";
    private static final String LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * get random instance
     * @return random
     */
    public static Random getRandom() {
        random = new Random(System.currentTimeMillis());
        return random;
    }

    /**
     * get the random number which limit 0 to max-1
     * @param max
     * @return
     */
    public static int random(int max){
        if (max <= 0) {
            logger.info("The max elt 0!");
            return 0;
        }
        return random.nextInt(max);
    }

    /**
     * get the specified area of string which limit min - max
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max) {
        if (max < min) {
            logger.info("The max should greater than the min!");
        }
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * get the specified length string of number value
     * @param length
     * @return
     */
    public static String randomNum(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(NUMBER_CHARS.charAt(random(NUMBER_CHARS.length())));
        }
        return builder.toString();
    }

    /**
     * get the specified length lower case string
     * @param length
     * @return
     */
    public static String randomLowerStr(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(LOWER_CASE_CHARS.charAt(random(LOWER_CASE_CHARS.length())));
        }
        return builder.toString();
    }

    /**
     * get the specified length upper case string
     * @param length
     * @return
     */
    public static String randomUpperStr(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(UPPER_CASE_CHARS.charAt(random(UPPER_CASE_CHARS.length())));
        }
        return builder.toString();
    }

    /**
     * get the specified length string
     * @param length
     * @return
     */
    public static String randomStr(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(UPPER_CASE_CHARS.concat(LOWER_CASE_CHARS).charAt(UPPER_CASE_CHARS.length() + LOWER_CASE_CHARS.length()));
        }
        return builder.toString();
    }

    /**
     * get the specified length string which include number and letter
     * @param length
     * @return
     */
    public static String randomChar(int length) {
        StringBuilder builder = new StringBuilder(length);
        String temp = UPPER_CASE_CHARS + LOWER_CASE_CHARS + NUMBER_CHARS;
        for (int i = 0; i < length; i++) {
            builder.append(temp.charAt(random(temp.length())));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        PropertyConfigurator.configure("extra/log4j.properties ");
        System.out.println(random(5, 8));
        System.out.println(random(-1));
    }
}
