package com.smart.dao;

import com.smart.bean.Account;
import com.smart.bean.User;
import org.apache.log4j.Logger;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class AccountDao {

    private static final Logger logger = Logger.getLogger(AccountDao.class);

    @Inject
    private NutDao dao;

    /**
     * get the max id
     * @return
     */
    public int getMax() {
        Sql sql = Sqls.create("select max(account_id) from t_account");
        sql.setCallback(Sqls.callback.integer());
        dao.execute(sql);
        return sql.getInt();
    }

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
     * get Account by mobile
     * @param mobile
     * @return
     */
    public Account getAccountByMobile(String mobile) {
        Cnd cnd = Cnd.where("mobile", "=", mobile);
        Account account = dao.fetch(Account.class, cnd);
        return account;
    }

    /**
     * get Account by username or mobile number
     * @param source
     * @return
     */
    public Account getAccount(String source) {
        Cnd cnd = Cnd.where("username", "=", source).or("mobile", "=", source);
        Account account = dao.fetch(Account.class, cnd);
        return account;
    }

    /**
     * get Account by account_id
     * @param id
     * @return
     */
    public Account getAccount(int id) {
        return dao.fetch(Account.class, id);
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
     * insert account
     * @param account
     */
    public void insert(Account account) {
        dao.insert(account);
    }

    /**
     * update account
     * @param account
     */
    public void update(Account account) {
        dao.update(account);
    }

}
