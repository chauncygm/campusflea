package com.smart.dao;

import com.smart.bean.Campus;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class CampusDao {

    @Inject
    private NutDao dao;

    /**
     * find campus by area id
     * @param areaId
     * @return
     */
    public List<Campus> findCampusByAreaId(int areaId) {
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
