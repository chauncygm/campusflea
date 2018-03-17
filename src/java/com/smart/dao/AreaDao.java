package com.smart.dao;

import com.smart.bean.Area;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class AreaDao {

    @Inject
    private NutDao dao;

    /**
     * get sub area by father area
     * @param areaId
     * @return
     */
    public List<Area> getSubArea(int areaId) {
        Cnd cnd = Cnd.where("father_area", "=", areaId);
        return dao.query(Area.class, cnd);
    }

    /**
     * get root area
     * @return
     */
    public List<Area> getArea() {
        Cnd cnd = Cnd.where("level", "=", 1);
        return dao.query(Area.class, cnd);
    }

}
