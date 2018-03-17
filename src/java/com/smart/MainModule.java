package com.smart;

import com.smart.dao.AccountDao;
import com.smart.dao.DaoManager;
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
    private AccountDao accountDao;

    @At("/")
    public void index() {
        System.out.println("test dao:" + accountDao);
        System.out.println("test daomanager: " + DaoManager.getInstance().getAccountDao());
    }


}
