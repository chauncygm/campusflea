package com.smart.dao;

import com.smart.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

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
        return dao.count(User.class, Cnd.where("user_id", "=", id)) > 0;
    }


    public void insert(User user) {
        dao.insert(user);
    }

    /**
     * get user by id
     * @param id
     * @return
     */
    public User get(int id) {
        return dao.fetch(User.class, id);
    }

    /**
     * update user
     * @param user
     */
    public void update(User user) {
        dao.update(user);
    }

}
