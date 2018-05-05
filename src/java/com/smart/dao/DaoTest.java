package com.smart.dao;

import com.smart.MainModule;
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
    public void after() {
        if (ioc != null) {
            ioc.depose();
        }
    }

}
