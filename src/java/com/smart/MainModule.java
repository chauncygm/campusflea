package com.smart;

import com.smart.dao.AccountDao;
import com.smart.dao.DaoManager;
import com.smart.utils.Log;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import java.io.File;

@IocBean
@SetupBy(MainSetup.class)
@IocBy(type= ComboIocProvider.class, args={
        "*js","ioc/",
        "*anno","com.smart",
        "*tx",})
public class MainModule {

    private static final Logger logger = Logger.getLogger(MainModule.class);

    @Inject
    private AccountDao accountDao;

    @At("/")
    public void index() {
        System.out.println("dao:" + accountDao);
        System.out.println("daomanager: " + DaoManager.getInstance().getAccountDao());
    }

}
