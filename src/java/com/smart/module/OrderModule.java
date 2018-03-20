package com.smart.module;

import com.smart.bean.Order;
import com.smart.dao.OrderDao;
import com.smart.filter.AuthFilter;
import org.apache.log4j.Logger;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;


@At("/order")
@IocBean
public class OrderModule {

    private static final Logger logger = Logger.getLogger(Order.class);

    @Inject
    private OrderDao orderDao;

    /**
     * 查看订单，添加订单，操作订单
     */

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    public Object addOrder() {
        return null;
    }

    @At
    @Ok("json")
    @Filters(@By(type= AuthFilter.class))
    private Object queryOrder() {
        return null;
    }

}
