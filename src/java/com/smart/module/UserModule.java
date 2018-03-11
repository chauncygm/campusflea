package com.smart.module;

import com.smart.bean.Account;
import com.smart.struct.CommonResult;
import com.smart.dao.UserDao;
import com.smart.struct.ResultMgr;
import com.smart.utils.Md5Util;
import com.smart.utils.StringUtil;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@At("/user")
public class UserModule {

    private static final Logger logger = Logger.getLogger(UserModule.class);

    @Inject
    private UserDao userDao;

    /**
     * 检测手机号是否已被注册
     * @param mobile
     * @return
     */
    @At
    public Object checkAccount(@Param("mobile") String mobile) {
        if (StringUtil.isBlank(mobile)) {
            logger.debug("参数为空");
            return ResultMgr.paramError();
        }
        if (!userDao.count(mobile)) {
            logger.info(mobile + "已被注册");
            return ResultMgr.success("该手机号已被注册", false);
        }
        return ResultMgr.success(true);
    }

    /**
     * 用户注册
     * @param mobile
     * @param password
     * @return
     */
    @At
    public Object addUser(@Param("mobile") String mobile,@Param("password") String password) {
        if (StringUtil.isBlank(mobile) || StringUtil.isBlank(password)) {
            logger.debug("参数错误");
            return ResultMgr.paramError();
        }
        long now = new Date().getTime();
        String pswd = Md5Util.md5(password + String.valueOf(now));
        Account account = new Account(mobile, pswd);
        account.setCreatTime(now);
        try {
            userDao.insert(account);
            logger.info("新用户注册成功！" + account);
            return ResultMgr.success("注册成功", true);
        } catch (Exception e) {
            logger.info("用户注册失败！" + account);
            return ResultMgr.error();
        }
    }

    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    @At
    public Object login(@Param("mobile") String mobile,
                        @Param("password") String password, HttpServletRequest requset) {

        if (StringUtil.isBlank(mobile) || StringUtil.isBlank(password)) {
            logger.info("参数不能为空");
            return ResultMgr.paramError();
        }
        if (StringUtil.isMobileNum(mobile)) {
            logger.info("错误的手机号！" + mobile);
            return ResultMgr.success("错误的手机号", false);
        }
        Account account = userDao.findAccount(mobile);
        if (account == null) {
            logger.info("未注册的手机号" + mobile);
            return ResultMgr.success("用户不存在", false);
        }
        String pswd = Md5Util.md5(password + account.getCreatTime());
        if (!account.getPassword().equals(pswd)) {
            return ResultMgr.success("密码不匹配", false);
        }
        return ResultMgr.success();
    }

}
