package com.smart.dao;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

public class DaoManager {

    /**
     * 枚举实现单例
     */
    private DaoManager(){}
    private enum Singleton{
        INSTANCE;
        DaoManager processor = new DaoManager();
        DaoManager getProcessor () {
            return processor;
        }
    }
    public static DaoManager getInstance() {
        return Singleton.INSTANCE.getProcessor();
    }

    private AccountDao accountDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

}
