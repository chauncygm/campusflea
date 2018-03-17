package com.smart.module;

import com.smart.bean.Account;
import com.smart.common.Constant;
import com.smart.dao.AccountDao;
import com.smart.filter.SignFilter;
import com.smart.service.SMS;
import com.smart.struct.CommonResult;
import com.smart.struct.LoginPara;
import com.smart.utils.*;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@At("/user")
@IocBean
public class AccountModule {

    private static final Logger logger = Logger.getLogger(AccountModule.class);

    private static final int CAPTCHA_CODE_LENGTH = 6;
    private static final int EXPIRE_TIME = 60 * 1000;

    /**
     * user captcha map
     * key: mobile | value: captcha code
     */
    private static final ConcurrentHashMap<String, String> userCaptchaMap = new ConcurrentHashMap<>();

    @Inject
    private AccountDao accountDao;

    /**
     * check the username
     * @param username
     * @return
     */
    @At
    @Ok("json")
    public Object checkUserName(@Param("username") String username) {
        if (StringUtil.isBlank(username)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the username is empty or null");
        }
        if (accountDao.checkUserName(username)) {
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
    @Ok("json")
    public Object checkAccount(@Param("mobile") String mobile) {
        if (StringUtil.isBlank(mobile)) {
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile number is empty or null");
        }
        if (!accountDao.checkMobileNumber(mobile)) {
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
        CommonResult result = null;
        if ((result = ValidateUtil.checkMobileNum(mobile)) != null) {
            return result;
        }
        String captchaCode = RUtil.randomNum(CAPTCHA_CODE_LENGTH);
        if (SMS.sendCaptchaCode(mobile, captchaCode)) {
            long now = new Date().getTime();
            userCaptchaMap.put(mobile, captchaCode + (now + EXPIRE_TIME));
            return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "send captcha code success");
        }
        return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "send captcha code failed");
    }

    /**
     * user register
     * @param loginPara register info
     * @return
     */
    @At
    @Ok("json")
    public Object register(@Param("..") LoginPara loginPara) {
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
            accountDao.insert(account);
            return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "register succeed");
        } catch (Exception e) {
            logger.error("user register faild: " + loginPara);
        }
        return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "register failed");
    }

    /**
     * login
     * @param
     * @return
     */
    @At
    @Ok("json")
    public Object login(@Param("username") String username, @Param("password") String password) {
        CommonResult result = null;
        //check username is right username or mobile number
        if ((result =ValidateUtil.checkUserName(username)) != null
                && (result = ValidateUtil.checkMobileNum(username)) != null) {
            return result;
        }
        try {
            Account account = accountDao.getAccount(username);
            if (account == null) {
                return new CommonResult(Constant.RESCODE_USER_NOTEXIST, "user not exist");
            }
            String pwd = Md5Util.md5(password +account.getCreatTime());
            if (!account.getPassword().equals(password)) {
                return new CommonResult(Constant.RESCODE_PASSWORD_ERROR, "password error");
            }
            String token = JwtHelper.createJWT(String.valueOf(account.getAccountId()),
                    account.getUsername() + account.getPassword(),
                    Constant.TOKEN_EXPIRE_TIME, Constant.SECRETKEY);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "login succeed", account.getAccountId(), token);
        } catch (Exception e) {
            logger.info("user login faild: username[" + username + "] password [" + password + "]");
        }
        return  new CommonResult(Constant.RESCODE_REQUEST_ERROR, "login failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type = SignFilter.class))
    public Object resetPwd(@Param("id") String id, @Param("newPass") String newPass, HttpServletRequest request) {
        CommonResult result = null;
        if ((result = ValidateUtil.checkPwd(newPass)) != null) {
            return result;
        }
        try {
            Account account = accountDao.getAccount(Integer.valueOf(id));
            if (account == null) {
                return new CommonResult(Constant.ESCODE_ACCOUNT_NOTEXIST, "account not exist");
            }
            account.setPassword(Md5Util.md5(newPass + account.getCreatTime()));
            accountDao.update(account);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "reset password succeed");
        } catch (Exception e) {
            logger.error("user reset password faild: id[" + id + "] newPass[" + newPass + "]");
        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "reset password failed");
    }

    @At
    @Ok("json")
    public String test() {
        return "ojbk";
    }

    @At
    @Ok("json")
    @Filters(@By(type = SignFilter.class))
    public String testtoken(@Param("id") String id) {
        return "complete objk";
    }

}
