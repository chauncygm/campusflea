package com.smart.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isBlank(String source) {
        if (source == null) {
            return true;
        }
        return source.isEmpty();
    }

}
