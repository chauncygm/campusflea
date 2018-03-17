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

    /**
     * insert comments
     * @param comments
     */
    public void insert(Comments comments) {
        dao.insert(comments);
    }

    /**
     * list all comment by goods id
     * @param goodsId
     * @return
     */
    public List<Comments> getComment(int goodsId) {
        Cnd cnd = Cnd.where("goods_id", "=", goodsId);
        return dao.query(Comments.class, cnd);
    }
}
