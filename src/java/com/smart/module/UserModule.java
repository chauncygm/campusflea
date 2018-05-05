package com.smart.module;

import com.smart.bean.Area;
import com.smart.bean.Campus;
import com.smart.bean.Feedback;
import com.smart.bean.User;
import com.smart.common.Constant;
import com.smart.dao.AreaDao;
import com.smart.dao.CampusDao;
import com.smart.dao.FeedbackDao;
import com.smart.dao.UserDao;
import com.smart.filter.AccessControlFilter;
import com.smart.filter.AuthFilter;
import com.smart.struct.CommonResult;
import com.smart.dto.UserInfo;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@At("/user")
@IocBean
public class UserModule {

    private static final Logger logger = Logger.getLogger(UserModule.class);

    @Inject
    private UserDao userDao;

    @Inject
    private AreaDao areaDao;

    @Inject
    private CampusDao campusDao;

    @Inject
    private FeedbackDao feedbackDao;

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class)})
    public Object isExist(@Param("id") int id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean isExist = userDao.isExist(id);
        logger.info("user is exist : " + id + "|" + isExist);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", isExist);
    }

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
    public Object getArea(@Param("areaId") int areaId) {
        List<Area> areas = areaDao.getSubArea(areaId);
        logger.info("get area  by father_area : " + areaId);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", areas);
    }

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
    public Object getCampus(@Param("areaId") int areaId) {
        List<Campus> areas = campusDao.getCampus(areaId);
        logger.info("get campus by areaId : " + areaId);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", areas);
    }

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
    public Object getUserInfo(@Param("id") int id) {
        User user = userDao.get(id);
        UserInfo userInfo = new UserInfo(user);
        String city = areaDao.getArea(user.getAreaId()).getAreaName();
        String province = areaDao.getArea(areaDao.getArea(user.getAreaId()).getFatherArea()).getAreaName();
        String campus = campusDao.getCampusName(user.getCampusId());
        userInfo.setCampus(campus);
        userInfo.setLocation(province + "??" + city + "??" + campus);
        logger.info(userInfo.toString());
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok", userInfo);
    }

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
    public Object addUser(@Param("..") User userinfo, HttpServletRequest request) {
        System.out.println(userinfo);
        int userId = (int) request.getAttribute("userId");
        userinfo.setUserId(userId);
        userDao.insert(userinfo);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
    public Object updateUser(@Param("..") User userinfo, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        User user = userDao.get(userId);
        user.setNickname(userinfo.getNickname());
        user.setRealname(userinfo.getRealname());
        user.setSex(userinfo.getSex());
        user.setEmail(userinfo.getEmail());
        user.setIntroduce(userinfo.getIntroduce());
        userDao.update(user);
        return new CommonResult(Constant.RESCODE_REQUEST_OK, "ok");
    }

    @At
    @Ok("json")
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
    public Object listFriends(HttpServletRequest request) {
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
    @Filters({@By(type=AuthFilter.class), @By(type=AccessControlFilter.class)})
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
