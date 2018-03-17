package com.smart.dao;

import com.smart.bean.Feedback;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class FeedbackDao {

    @Inject
    private NutDao dao;

    public void insert(Feedback feedback) {
        dao.insert(feedback);
    }

    /**
     * query feedback by userId
     * @param userId
     * @return
     */
    public List<Feedback> findByUserId(int userId) {
        Cnd cnd = Cnd.where("user_id", "=", userId);
        return dao.query(Feedback.class, cnd);
    }

    /**
     * get all feedback
     * @return
     */
    public List<Feedback> listAll() {
        return dao.query(Feedback.class, null);
    }
}
