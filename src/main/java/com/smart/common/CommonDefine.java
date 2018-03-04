package com.smart.common;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonDefine {

    private static final Logger logger = Logger.getLogger(CommonDefine.class);

    private static final String CONF_COMMON_PATH = "conf/common.properties";
    private static final String INIT_CREDIT_SCORE_FIELD = "init_credit_sscore";

    public static String INIT_CREDIT_SCORE;

    private static Properties properties = new Properties();

    static  {
        load();
    }

    public static void load() {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource(CONF_COMMON_PATH).getPath();
            properties.load(new FileInputStream(path));
            INIT_CREDIT_SCORE = properties.getProperty(INIT_CREDIT_SCORE_FIELD);
            if (INIT_CREDIT_SCORE == null) {
                logger.error("配置文件：" + CONF_COMMON_PATH + "[" + INIT_CREDIT_SCORE_FIELD +"]不存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("加载配置文件:" + CONF_COMMON_PATH + " 出错");
        }
    }

}
