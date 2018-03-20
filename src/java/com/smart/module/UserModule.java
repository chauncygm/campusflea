package com.smart.module;

import com.smart.bean.Feedback;
import com.smart.bean.User;
import com.smart.common.Constant;
import com.smart.dao.FeedbackDao;
import com.smart.dao.UserDao;
import com.smart.filter.AuthFilter;
import com.smart.struct.CommonResult;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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
    @Filters(@By(type= AuthFilter.class))
    private Object listFriends(HttpServletRequest request) {
        ArrayList<User> friends = new ArrayList<>();
        try {
            int userId = (int) request.getAttribute("userId");
            String friendStr = userDao.get(userId).getFriends();
            String[] idstr = friendStr.split(",");
            for (String id : idstr) {
                User user = userDao.get(Integer.valueOf(id));
                if (user != null) {
                    friends.add(user);
                }
            }
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", friends);
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "failed");
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object updateInfo(@Param("..") User userinfo, HttpServletRequest request) {
        try {
            int userId = (int) request.getAttribute("userId");
            User user = userDao.get(userId);
            user.setNickname(userinfo.getNickname());
            user.setCampusId(userinfo.getCampusId());
            user.setRealname(userinfo.getRealname());
            userDao.update(user);
        } catch (Exception e) {

        }
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object submitFeedback(@Param("..") Feedback feedback, HttpServletRequest request) {
        try {
            int userId = (int) request.getAttribute("userId");
            feedback.setUserId(userId);
            feedback.setCreateTime(System.currentTimeMillis());
            feedbackDao.insert(feedback);
            return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
        } catch (Exception e) {

        }
        return new CommonResult(Constant.RESCODE_REQUEST_ERROR, "failed");
    }
}
