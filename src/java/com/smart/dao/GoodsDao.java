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

    public Goods get(int id) {
        return dao.fetch(Goods.class, id);
    }

    public void insert(Goods goods) {
        dao.insert(goods);
    }

    public List<Goods> getGoodsList(Condition cnd) {
        return dao.query(Goods.class, cnd);
    }

    public List<Goods> getGoodsList(Sql sql) {
        sql.setEntity(dao.getEntity(Goods.class));
        dao.execute(sql);
        return sql.getList(Goods.class);
    }

    public void update(Goods goods) {
        dao.update(goods);
    }

}
