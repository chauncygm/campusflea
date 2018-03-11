package com.smart.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static final String MOBILE_REGX = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    public static boolean isBlank(String source) {
        if (source == null) {
            return true;
        }
        return source.isEmpty();
    }

    public static boolean isMobileNum(String mobile) {
        Pattern pattern = Pattern.compile(MOBILE_REGX);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

}
