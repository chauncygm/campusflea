package com.smart.dao;

import com.smart.bean.Comments;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class CommentDao {

    @Inject
    public NutDao dao;

    public void insert(Comments comments) {
        dao.insert(comments);
    }

    public List<Comments> getComment(int goodsId) {
        Cnd cnd = Cnd.where("goods_id", "=", goodsId);
        return dao.query(Comments.class, cnd);
    }
}
