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
     * 账号是否已被注册
     * @param acount
     * @return
     */
    public boolean count(String acount) {
        Cnd cnd = Cnd.where("mobile", "=",acount);
        List<Account> list = dao.query(Account.class, cnd);
        return list.isEmpty();
    }

    /**
     *
     * @param account
     * @return
     */
    public boolean add(Account account) {
        return true;
    }

    /**
     * 是否存在用户
     * @param mobile
     * @return
     */
    public Account findAccount(String mobile) {
        Cnd cnd = Cnd.where("mobile", "=", mobile);
        Account at = dao.fetch(Account.class, cnd);
        return at;
    }

    /**
     * 通过id得到用户信息
     * @param userId
     * @return
     */
    public User getUser(int userId) {
        return dao.fetch(User.class, userId);
    }

    /**
     * 查找是否同名
     * @param nickname
     * @return
     */
    public boolean findByNickName(String nickname) {
        Cnd cnd = Cnd.where("nick_name", "=", nickname);
        int count = dao.count(User.class, cnd);
        return count > 0;
    }

    /**
     * 添加用户
     * @param account
     */
    public void insert(Account account) {
        dao.insert(account);
    }


    public boolean update(Account account) {
        int update = dao.update(account);
        return update > 0;
    }

    public boolean update(User user) {
        int update = dao.update(user);
        return update > 0;
    }
}
