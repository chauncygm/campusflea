package com.smart.dao;

import com.smart.bean.Campus;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class CampusDao {

    @Inject
    private NutDao dao;

    /**
     * get campus name by id
     * @param id
     * @return
     */
    public String getCampusName(int id) {
        Campus campus = dao.fetch(Campus.class, id);
        return campus.getCampusName();
    }

    /**
     * find campus by area id
     * @param areaId
     * @return
     */
    public List<Campus> getCampus(int areaId) {
        Cnd cnd = Cnd.where("area_id", "=", areaId);
        return dao.query(Campus.class, cnd);
    }

    /**
     * select campus by name
     * @param name
     * @return
     */
    public List<Campus> findCampusByName(String name) {
        Cnd cnd = Cnd.where("name", "like", name);
        return dao.query(Campus.class, cnd);
    }
}
