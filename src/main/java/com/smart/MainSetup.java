package com.smart;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Calendar;
import java.util.TimeZone;


public class MainSetup implements Setup {

    private static final Logger logger = Logger.getLogger(MainSetup.class);

    private static final String log4j_path = "properties/log4j.properties";

    public void init(NutConfig conf) {
        logger.info("--------MainSetup initing...--------");
        PropertyConfigurator.configure( Thread.currentThread().getContextClassLoader().getResource(log4j_path));
        Ioc ioc = conf.getIoc();
        Dao dao = ioc.get(Dao.class, "dao");
        Daos.createTablesInPackage(dao,"com.smart.bean",true);
        logger.info("连接数据库创建表完成");
        logger.info("--------MainSetup success--------");
    }

    public void destroy(NutConfig conf) {
        logger.info("--------MainSetup destroied--------");
    }

    public static void main(String[] args) {
        //测试加载log4j配置路径
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("properties/log4j.properties").toString());
        //查看本地时区
        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        System.out.println(timeZone.getID());
        System.out.println(timeZone.getDisplayName());
    }
}
