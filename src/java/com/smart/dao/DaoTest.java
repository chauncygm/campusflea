package com.smart.dao;

import com.smart.MainModule;
import com.smart.bean.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.IocLoader;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.mvc.annotation.IocBy;

public class DaoTest extends Assert {

    protected Ioc ioc;

    @Before
    public void before() throws Exception {
        IocBy iocBy = MainModule.class.getAnnotation(IocBy.class);
        IocLoader loader = new ComboIocLoader(iocBy.args());
        ioc = new NutIoc(loader);
    }

    @After
    public void after() throws Exception {
        if (ioc != null) {
            ioc.depose();
        }
    }

    @Test
    public void testUserDao() {
        UserDao userDao = ioc.get(UserDao.class);

//        System.out.println(userDao.count("111"));
//        System.out.println(userDao.count("222"));
//        System.out.println(userDao.count("333"));
//
//        System.out.println(userDao.findAccount("111","111"));
//        System.out.println(userDao.findAccount("111","1111"));
//
//        System.out.println(userDao.getUser(1));
//        System.out.println(userDao.getUser(2));

        userDao.insert(new Account("12121","343242"));
    }
}
