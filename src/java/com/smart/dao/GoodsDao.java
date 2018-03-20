package com.smart.dao;

import com.smart.bean.Goods;
import org.nutz.dao.Condition;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class GoodsDao {

    @Inject
    private NutDao dao;

    /**
     * get goods info
     * @param id
     * @return
     */
    public Goods get(int id) {
        return dao.fetch(Goods.class, id);
    }

    /**
     * insert goods info
     * @param goods
     */
    public void insert(Goods goods) {
        dao.insert(goods);
    }

    /**
     * query goods list by cnd
     * @param cnd
     * @return
     */
    public List<Goods> getGoodsList(Condition cnd) {
        return dao.query(Goods.class, cnd);
    }

    /**
     * query goods list by sql
     * @param sql
     * @return
     */
    public List<Goods> getGoodsList(Sql sql) {
        sql.setEntity(dao.getEntity(Goods.class));
        dao.execute(sql);
        return sql.getList(Goods.class);
    }

    /**
     * update goods info
     * @param goods
     */
    public void update(Goods goods) {
        dao.update(goods);
    }

}
