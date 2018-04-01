package com.smart.utils;

import com.smart.common.Constant;
import com.smart.struct.CommonResult;
import com.smart.struct.LoginPara;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final String USERNAME_REGX = "^[_a-zA-Z0-9]{4,10}$";

    private static final String MOBILE_REGX = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    private static final String PWD_REGX = "^[a-zA-Z]\\w{5,13}$";


    private static boolean check(String source, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

    public static CommonResult checkAccount(String source) {
        if (StringUtil.isBlank(source)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the account is empty or null");
        }
        if (!check(source, USERNAME_REGX) && !check(source, MOBILE_REGX)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the account is illegal");
        }
        return null;
    }

    public static CommonResult checkUserName(String username) {
        if (StringUtil.isBlank(username)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the username is empty or null");
        }
        if (!check(username, USERNAME_REGX)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the username is illegal");
        }
        return null;
    }

    public static CommonResult checkMobileNum(String mobile) {
        if (StringUtil.isBlank(mobile)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile is empty or null");
        }
        if (!check(mobile, MOBILE_REGX)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile is illegal");
        }
        return null;
    }

    public static CommonResult checkPwd(String password) {
        if (StringUtil.isBlank(password)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the password is empty or null");
        }
        if (!check(password, PWD_REGX)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the password is illegal");
        }
        return null;
    }

    public static CommonResult checkCaptchaCode(String code) {
        if (StringUtil.isBlank(code)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the captcha code is empty or null");
        }
        return null;
    }

    public static CommonResult checkLoginPara(LoginPara loginPara) {
        CommonResult result = checkUserName(loginPara.getUsername());
        if (result != null) return result;
        result =checkMobileNum(loginPara.getMobile());
        if (result != null) return result;
        result = checkPwd(loginPara.getPassword());
        if (result != null) return result;
        result =checkCaptchaCode(loginPara.getCaptchaCode());
        return result;
    }

    public static CommonResult check(LoginPara loginPara) {
        CommonResult result = checkMobileNum(loginPara.getMobile());
        if (result != null) return result;
        result = checkPwd(loginPara.getPassword());
        if (result != null) return result;
        result =checkCaptchaCode(loginPara.getCaptchaCode());
        return result;
    }
}
