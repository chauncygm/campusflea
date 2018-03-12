package com.smart.utils;

import com.smart.common.Constant;
import com.smart.struct.CommonResult;
import com.smart.struct.LoginPara;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final String USERNAME_REGX = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]{4,10}";

    private static final String MOBILE_REGX = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    private static final String PWD_REGX = "^[a-zA-Z]\\w{5,13}$";


    private static boolean check(String source, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

    public static boolean checkUserName(String username, CommonResult result) {
        if (StringUtil.isBlank(username)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the username is empty or null");
            return false;
        }
        if (!check(username, USERNAME_REGX)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the username is illegal");
            return false;
        }
        return true;
    }

    public static boolean checkMobileNum(String mobile, CommonResult result) {
        if (StringUtil.isBlank(mobile)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile is empty or null");
            return false;
        }
        if (!check(mobile, USERNAME_REGX)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile is illegal");
            return false;
        }
        return true;
    }

    public static boolean checkPwd(String password, CommonResult result) {
        if (StringUtil.isBlank(password)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the password is empty or null");
            return false;
        }
        if (!check(password, USERNAME_REGX)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the password is illegal");
            return false;
        }
        return true;
    }

    public static boolean checkCaptchaCode(String code, CommonResult result) {
        if (StringUtil.isBlank(code)) {
            result = new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the captcha code is empty or null");
            return false;
        }
        return true;
    }

    public static boolean checkLoginPara(LoginPara loginPara, CommonResult result) {
        return checkUserName(loginPara.getUsername(), result)
                && checkMobileNum(loginPara.getMobile(), result)
                && checkPwd(loginPara.getPassword(), result)
                && checkCaptchaCode(loginPara.getCaptchaCode(), result);
    }
}
