package com.smart.module;

import com.smart.bean.Account;
import com.smart.common.Constant;
import com.smart.dao.AccountDao;
import com.smart.filter.AccessControlFilter;
import com.smart.filter.AuthFilter;
import com.smart.service.SMS;
import com.smart.struct.CommonResult;
import com.smart.struct.LoginPara;
import com.smart.utils.*;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.AccessException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@At("/user")
@IocBean
public class AccountModule {

    private static final Logger logger = Logger.getLogger(AccountModule.class);

    private static final int CAPTCHA_CODE_LENGTH = 6;
    private static final int EXPIRE_TIME = 60 * 1000;
    private static final String REGIST_TYPE = "_regist";
    private static final String RESETPASS_TYPE = "_reset_pass";

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
    @Filters(@By(type= AccessControlFilter.class))
    public Object checkUserName(@Param("username") String username) {
        CommonResult result = null;
        if ((result = ValidateUtil.checkUserName(username)) != null) {
            logger.info("[error] username check failed : " +username);
            return result;
        }
        if (accountDao.checkUserName(username)) {
            logger.info("[error] username has been used : " + username);
            return  new CommonResult(Constant.RECODE_USERNAME_USED, "the username has been used");
        }
        logger.info("[ok] username : " + username);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    /**
     * check the mobile number
     * @param mobile
     * @return
     */
    @At
    @Ok("json")
    @Filters(@By(type= AccessControlFilter.class))
    public Object checkMobile(@Param("mobile") String mobile) {
        if (StringUtil.isBlank(mobile)) {
            logger.info("[error] mobile checked failed : " + mobile);
            return new CommonResult(Constant.RESCODE_CHECKPARAM_ERROR, "the mobile number is empty or null");
        }
        if (!accountDao.checkMobileNumber(mobile)) {
            logger.info("[error] mobile has registed : " +mobile);
            return new CommonResult(Constant.RESCODE_MOBILE_HASREGISTERD, "the mobile number has be register");
        }
        logger.info("[ok] mobile : " + mobile);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    /**
     * send captcha code
     * @param mobile
     * @return
     */
    @At
    @Ok("json")
    @Filters(@By(type= AccessControlFilter.class))
    public Object sendCaptchaCode(@Param("mobile") String mobile, @Param("type") String type) {
        CommonResult result = null;
        if ((result = ValidateUtil.checkMobileNum(mobile)) != null) {
            logger.info("[error] mobile : " + mobile);
            return result;
        }
        String captchaCode = RUtil.randomNum(CAPTCHA_CODE_LENGTH);
//        if (SMS.sendCaptchaCode(mobile, captchaCode)) {
            long now = new Date().getTime();
            logger.info(captchaCode);
            userCaptchaMap.put(mobile + type, captchaCode + (now + EXPIRE_TIME));
            return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "send captcha code success");
//        }
//        return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "send captcha code failed");
    }

    /**
     * user register
     * @param loginPara register info
     * @return
     */
    @At
    @Ok("json")
    @Filters(@By(type= AccessControlFilter.class))
    public Object register(@Param("..") LoginPara loginPara) {
        CommonResult result = null;
        //check register param
        if ((result = ValidateUtil.checkLoginPara(loginPara)) != null) {
            logger.info("[error] loginPara error : " + loginPara);
            return result;
        }
        //check user name
        if (accountDao.checkUserName(loginPara.getUsername())) {
            logger.info("[error] username has been used : " + loginPara.getUsername());
            return  new CommonResult(Constant.RECODE_USERNAME_USED, "the username has been used");
        }
        if (accountDao.checkMobileNumber(loginPara.getMobile())) {
            logger.info("[error] mobile has been regist : " + loginPara.getUsername());
            return new CommonResult(Constant.RESCODE_MOBILE_HASREGISTERD, "the mobile has been regist");
        }
        //check the captcha code
        if (userCaptchaMap.get(loginPara.getMobile() + REGIST_TYPE) == null) {
            logger.info("[error] no captcha code find : " + loginPara.getCaptchaCode());
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_ERROR, "no captcha code find");
        }
        if (!loginPara.getCaptchaCode().equalsIgnoreCase(userCaptchaMap.get(loginPara.getMobile() + REGIST_TYPE).substring(0,6))) {
            logger.info("[error] captcha code is error : " +loginPara.getCaptchaCode());
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_ERROR, "captcha code is error");
        }
        long now = new Date().getTime();
        if (now > Long.valueOf(userCaptchaMap.get(loginPara.getMobile() + REGIST_TYPE).substring(6))) {
            logger.info("[error] captcha code timeout :" + loginPara.getCaptchaCode());
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_TIMEOUT, "captcha code has been timeout");
        }
        //persist user to database
        try {
            //md5 encrypt the password with the salt(create time);
            String pswd = Md5Util.md5(loginPara.getPassword() + String.valueOf(now));
            Account account = new Account(loginPara.getUsername(), loginPara.getMobile(), pswd);
            account.setCreatTime(now);
            accountDao.insert(account);
            logger.info("[ok] regist succeed : " + loginPara);
            return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "register succeed");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[error] regist faild: " + loginPara);
            return new CommonResult(Constant.RESCODE_OPERATE_FAIL, "register failed");
        }
    }

    @At
    @Ok("json")
    @Filters(@By(type=AccessControlFilter.class))
    public Object forgetPassword(@Param("..") LoginPara loginPara) {
        CommonResult result = null;
        if ((result = ValidateUtil.check(loginPara)) != null) {
            logger.info("[error] check param error : " + loginPara);
            return result;
        }
        Account account = accountDao.getAccountByMobile(loginPara.getMobile());
        if (account == null) {
            logger.info("[error] mobile account not exist : " + loginPara);
            return new CommonResult(Constant.ESCODE_ACCOUNT_NOTEXIST, "mobile account not exist");
        }
        if (userCaptchaMap.get(loginPara.getMobile() + RESETPASS_TYPE) == null) {
            logger.info("[error] no captcha code find : " + loginPara.getCaptchaCode());
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_ERROR, "no captcha code find");
        }
        if (!loginPara.getCaptchaCode().equalsIgnoreCase(userCaptchaMap.get(loginPara.getMobile() + RESETPASS_TYPE).substring(0,6))) {
            logger.info("[error] captcha code is error : " +loginPara.getCaptchaCode());
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_ERROR, "captcha code is error");
        }
        long now = new Date().getTime();
        if (now > Long.valueOf(userCaptchaMap.get(loginPara.getMobile() + RESETPASS_TYPE).substring(6))) {
            logger.info("[error] captcha code timeout :" + loginPara.getCaptchaCode());
            return new CommonResult(Constant.RESCODE_CAPTCHACODE_TIMEOUT, "captcha code has been timeout");
        }
        account.setPassword(Md5Util.md5(loginPara.getPassword() + String.valueOf(account.getCreatTime())));
        accountDao.update(account);
        logger.info("[ok] reset pswd succesd" + loginPara);
        return new CommonResult(Constant.RESCODE_OPERATE_SUCCEED, "reset pswd succeed");
    }

    /**
     * login
     * @param
     * @return
     */
    @At
    @Ok("json")
    @Filters(@By(type= AccessControlFilter.class))
    public Object login(@Param("username") String username, @Param("password") String password) {
        CommonResult result = null;
        //check username is right username or mobile number
        if ((result =ValidateUtil.checkAccount(username)) != null) {
            logger.info("login check error : " + username);
            return result;
        }
        try {
            Account account = accountDao.getAccount(username);
            if (account == null) {
                logger.info("[error] user not exist : " + username);
                return new CommonResult(Constant.RESCODE_USER_NOTEXIST, "user not exist");
            }
            String pwd = Md5Util.md5(password +account.getCreatTime());
            if (!account.getPassword().equals(pwd)) {
                logger.info("[error] password error : " + password);
                return new CommonResult(Constant.RESCODE_PASSWORD_ERROR, "password error");
            }
            String token = JwtHelper.createJWT(String.valueOf(account.getAccountId()),
                    account.getUsername() + account.getPassword(),
                    Constant.TOKEN_EXPIRE_TIME, Constant.SECRETKEY);
            logger.info("[ok] login ok , token : " + token);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "login succeed", account.getAccountId(), token);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("[error] user login faild: username[%s] password [%s]", username, password));
            return  new CommonResult(Constant.RESCODE_REQUEST_ERROR, "login failed");
        }
    }

    /**
     * reset password
     * @param id
     * @param newPass
     * @param request
     * @return
     */
    @At
    @Ok("json")
    @Filters(@By(type= AccessControlFilter.class))
    public Object resetPassword(@Param("id") String id, @Param("newPass") String newPass, HttpServletRequest request) {
        CommonResult result = null;
        if ((result = ValidateUtil.checkPwd(newPass)) != null) {
            logger.info("check pwd error : " + newPass);
            return result;
        }
        try {
            Account account = accountDao.getAccount(Integer.valueOf(id));
            if (account == null) {
                logger.info("account not exist : " + id);
                return new CommonResult(Constant.ESCODE_ACCOUNT_NOTEXIST, "account not exist");
            }
            account.setPassword(Md5Util.md5(newPass + account.getCreatTime()));
            accountDao.update(account);
            logger.info("reset pwd ok : " +newPass);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "reset password succeed");
        } catch (Exception e) {
            logger.error(String.format("user reset password faild: id[" + id + "] newPass[" + newPass + "]"));
            return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "reset password failed");
        }
    }

    /**
     * token auto login
     */
    @At
    @Ok("json")
    @Filters({@By(type = AuthFilter.class),@By(type= AccessControlFilter.class)})
    public CommonResult testtoken(@Param("id") String id) {
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

}
