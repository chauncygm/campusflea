package com.smart.dao;

import com.smart.bean.Message;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.OrderBy;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class MessageDao {

    @Inject
    private NutDao dao;

    /**
     * insert message
     * @param message
     */
    public void insert(Message message) {
        dao.insert(message);
    }

    /**
     * get total message count
     * @param mid
     * @param oid
     * @return
     */
    public int count(int mid, int oid) {
        SqlExpressionGroup group1 = Cnd.exps("send_id", "=", mid).and("replyId", "=", oid);
        SqlExpressionGroup group2 = Cnd.exps("send_id", "=", oid).and("replyId", "=", mid);
        Cnd cnd = Cnd.where(group1).or(group2);
        return dao.count(Message.class, cnd);
    }

    /**
     * query chat history
     * @param mid
     * @param oid
     * @return
     */
    public List<Message> getHistory(int mid, int oid, Pager pager) {
        SqlExpressionGroup group1 = Cnd.exps("send_id", "=", mid).and("replyId", "=", oid);
        SqlExpressionGroup group2 = Cnd.exps("send_id", "=", oid).and("replyId", "=", mid);
        Condition cnd = Cnd.where(group1).or(group2).orderBy("send_time", "DESC");
        return dao.query(Message.class, cnd, pager);
    }

}
