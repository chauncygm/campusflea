package com.smart;

import com.smart.dao.UserDao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBean
@SetupBy(MainSetup.class)
@IocBy(type= ComboIocProvider.class, args={
        "*js","ioc/",
        "*anno","com.smart",
        "*tx",})
public class MainModule {

    @Inject
    private UserDao userDao;

    @At("/")
    @Ok("jsp:home")
    public void index() {
        System.out.println(userDao);
    }


}
