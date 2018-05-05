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

    public List<Feedback> listAll() {
        return dao.query(Feedback.class, null);
    }
}
