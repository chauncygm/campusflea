package com.smart.module;

import com.smart.dao.GoodsDao;
import com.smart.filter.SignFilter;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

@At("goods")
@IocBean
public class GoodsModule {

    private static final Logger logger = Logger.getLogger(GoodsModule.class);

    @Inject
    private GoodsDao goodsDao;

    /**
     * put goods info
     */
    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object addGoods() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object goodsList() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object goodsDetail() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object hitLover() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= SignFilter.class))
    public Object addComment() {
        return null;
    }

}

