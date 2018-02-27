package com.smart;

import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(MainSetup.class)
@IocBy(type= ComboIocProvider.class, args={
        "*js","ioc/",
        "*anno","com.smart",
        "*tx",})
public class MainModule {

    @At("/")
    @Ok("jsp:home")
    public void index() {}
}
