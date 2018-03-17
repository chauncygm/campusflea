package com.smart.dao;

import com.smart.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class UserDao {

    @Inject
    private NutDao dao;

    /**
     * check user exist
     * @param id
     * @return
     */
    public boolean isExist(int id) {
        return dao.count(User.class, Cnd.where("id", "=", id)) > 0;
    }
}
