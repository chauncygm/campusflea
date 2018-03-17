package com.smart.module;

import com.smart.dao.FeedbackDao;
import com.smart.dao.UserDao;
import com.smart.filter.SignFilter;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

@At("/user")
@IocBean
public class UserModule {

    private static final Logger logger = Logger.getLogger(UserModule.class);

    @Inject
    private UserDao userDao;

    @Inject
    private FeedbackDao feedbackDao;

    /**
     * 查看好友，修改个人信息，信用积分，提交反馈
     */

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    private Object listFriends() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object updateInfo() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object submitFeedback() {
        return null;
    }
}
