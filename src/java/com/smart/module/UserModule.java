package com.smart.module;

import com.smart.bean.Account;
import com.smart.common.Constant;
import com.smart.dao.UserDao;
import com.smart.filter.SignFilter;
import com.smart.struct.CommonResult;
import com.smart.struct.LoginPara;
import com.smart.utils.*;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@At("/user")
@IocBean
public class UserModule {

    private static final Logger logger = Logger.getLogger(UserModule.class);

    private static final int CAPTCHA_CODE_LENGTH = 6;
    private static final int EXPIRE_TIME = 60 * 1000;

    /**
     * user captcha map
     * key: mobile | value: captcha code
     */
    private static final ConcurrentHashMap<String, String> userCaptchaMap = new ConcurrentHashMap<>();

    @Inject
    private UserDao userDao;

    /**
     * check the username
     * @param username
     * @return
     */
    @At
    public Object checkUserName(@Param("username") String username) {
        if (StringUtil.isBlank(username)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the username is empty or null");
        }
        if (userDao.checkUserName(username)) {
            return  new CommonResult(Constant.RECODE_USERNAME_USED, "the username has been used");
        }
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    /**
     * check the mobile number
     * @param mobile
     * @return
     */
    @At
    public Object checkAccount(@Param("mobile") String mobile) {
        if (StringUtil.isBlank(mobile)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile number is empty or null");
        }
        if (!userDao.checkMobileNumber(mobile)) {
            return new CommonResult(Constant.RESCODE_MOBILE_HASREGISTERD, "the mobile number has be register");
        }
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    /**
     * send captcha code
     * @param mobile
     * @return
     */
    @At
    @Ok("json")
    public Object sendCaptchaCode(@Param("mobile") String mobile) {
        System.out.println(mobile);
        CommonResult result = null;
        //check mobile number
        if ((result =ValidateUtil.checkMobileNum(mobile)) != null) {
            return result;
        }
        String captchaCode = RUtil.randomNum(CAPTCHA_CODE_LENGTH);
        try {
            //send captcha code to user's mobile
            System.out.println(captchaCode);
            long now = new Date().getTime();
            userCaptchaMap.put(mobile, captchaCode + (now + EXPIRE_TIME));
        } catch (Exception e) {
            return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "send captcha code failed");
        }
        return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "send captcha code success");
    }

    /**
     * user register
     * @param loginPara register info
     * @return
     */
    @At
    @Ok("json")
    public Object addUser(@Param("..") LoginPara loginPara) {
        CommonResult result = null;
        //check register param
        if ((result = ValidateUtil.checkLoginPara(loginPara)) != null) {
            return result;
        }
        //check the captcha code
        if (userCaptchaMap.get(loginPara.getMobile()) == null) {
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_ERROR, "no captcha code find");
        }
        if (!loginPara.getCaptchaCode().equalsIgnoreCase(userCaptchaMap.get(loginPara.getMobile()).substring(0,6))) {
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_ERROR, "captcha code is error");
        }
        long now = new Date().getTime();
        if (now > Long.valueOf(userCaptchaMap.get(loginPara.getMobile()).substring(6))) {
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_TIMEOUT, "captcha code has been timeout");
        }
        //persist user to database
        try {
            //md5 encrypt the password with the salt(create time);
            String pswd = Md5Util.md5(loginPara.getPassword() + String.valueOf(now));
            Account account = new Account(loginPara.getUsername(), loginPara.getMobile(), pswd);
            account.setCreatTime(now);
            userDao.insert(account);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "register failed");
        }
        return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "register succeed");
    }

    /**
     * login
     * @param loginPara
     * @return
     */
    @At
    @Ok("json")
    public Object login(@Param("..") LoginPara loginPara) {
        CommonResult result = null;
//        if ((result =ValidateUtil.checkUserName(loginPara.getUsername())) != null
//                && (result = ValidateUtil.checkMobileNum(loginPara.getMobile())) != null) {
//            return result;
//        }
        System.out.println(loginPara);
        try {
            Account account = userDao.getAccount(loginPara.getUsername());
            if (account == null) {
                return new CommonResult(Constant.RESCODE_USER_NOTEXIST, "user not exist");
            }
            String password = Md5Util.md5(loginPara.getPassword() +account.getCreatTime());
            if (!account.getPassword().equals(password)) {
                return new CommonResult(Constant.RESCODE_PASSWORD_ERROR, "password error");
            }
            String token = JwtHelper.createJWT(String.valueOf(account.getAccountId()), account.getUsername(),
                    account.getPassword(), Constant.TOKEN_EXPIRE_TIME, Constant.SECRETKEY);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "login succeed", null, token);
        } catch (Exception e) {
            return  new CommonResult(Constant.RESCODE_REQUEST_ERROR, "login failed");
        }
    }

    @At
    @Ok("json")
    @Filters(@By(type=SignFilter.class))
    public Object resetPassword(@Param("id") String id, @Param("newpass") String newpass) {
        CommonResult result = null;
        return result;
    }

    @At
    @Ok("json")
    @Filters(@By(type = SignFilter.class))
    public String requestJWT() {
        System.out.println("ooooooooooooooooooooookkkkkkkkkkkkkkk");
        return Constant.SECRETKEY;
    }

    @At
    @GET
    @Ok("json")
    public String test() {
        return "111111111111111111";
    }


}
