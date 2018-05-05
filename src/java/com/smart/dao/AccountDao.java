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

    public int getMaxId() {
        Sql sql = Sqls.create("select max(account_id) from t_account");
        sql.setCallback(Sqls.callback.integer());
        dao.execute(sql);
        return sql.getInt();
    }

    public boolean checkUserNameExist(String username) {
        Cnd cnd = Cnd.where("username", "=", username);
        return dao.count(Account.class, cnd) > 0;
    }

    public boolean checkMobileNumberExist(String mobile) {
        Cnd cnd = Cnd.where("mobile", "=",mobile);
        return dao.count(Account.class, cnd) > 0;
    }

    public Account getAccountByMobile(String mobile) {
        Cnd cnd = Cnd.where("mobile", "=", mobile);
        Account account = dao.fetch(Account.class, cnd);
        return account;
    }

    public Account getAccount(String source) {
        Cnd cnd = Cnd.where("username", "=", source).or("mobile", "=", source);
        Account account = dao.fetch(Account.class, cnd);
        return account;
    }

    public Account getAccount(int id) {
        return dao.fetch(Account.class, id);
    }

    public void insert(Account account) {
        dao.insert(account);
    }

    public void update(Account account) {
        dao.update(account);
    }

}
