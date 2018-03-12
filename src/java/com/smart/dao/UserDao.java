package com.smart.dao;

import com.smart.bean.Account;
import com.smart.bean.User;
import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    @Inject
    private NutDao dao;

    /**
     * check the username has been used
     * @param username
     * @return
     */
    public boolean checkUserName(String username) {
        Cnd cnd = Cnd.where("username", "=", username);
        return dao.count(Account.class, cnd) > 0;
    }


    /**
     * check the mobile number has been register
     * @param mobile
     * @return
     */
    public boolean checkMobileNumber(String mobile) {
        Cnd cnd = Cnd.where("mobile", "=",mobile);
        return dao.count(Account.class, cnd) > 0;
    }

    /**
     * get Account by username or mobile number
     * @param username
     * @param mobile
     * @return
     */
    public Account getAccount(String username, String mobile) {
        Cnd cnd = Cnd.where("username", "=", username).or("mobile", "=", mobile);
        Account at = dao.fetch(Account.class, cnd);
        return at;
    }

    /**
     * get user info by userId
     * @param userId
     * @return
     */
    public User getUser(int userId) {
        return dao.fetch(User.class, userId);
    }

    /**
     * check has same nickname
     * @param nickname
     * @return
     */
    public boolean findByNickName(String nickname) {
        Cnd cnd = Cnd.where("nick_name", "=", nickname);
        int count = dao.count(User.class, cnd);
        return count > 0;
    }

    /**
     * add user
     * @param account
     */
    public void insert(Account account) {
        dao.insert(account);
    }

}
