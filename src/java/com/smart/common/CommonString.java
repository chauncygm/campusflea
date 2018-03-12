package com.smart.common;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonString {

    private static final Logger logger = Logger.getLogger(CommonString.class);

    public static final String TIP_PATH = "extra/tip.properties";

    public static final String REQUEST_ERROR = "tip.request.error";
    public static final String REQUEST_SUCCESS = "tip.request.success";
    public static final String MOBAIL_EXIST = "tips.mobile.exist";
    public static final String ERROR_PASSWORD = "tip.password.error";
    public static final String VERIFICATION = "tip.verification.error";
    public static final String PASSWORD_DIFFER = "tip.password.differ";
    public static final String LOADING = "tip.loading";

    public static Properties Tip = new Properties();

    static {
        load();
    }

    private static void load() {
        try {
            Tip.load(new FileInputStream(TIP_PATH));
        } catch (IOException e) {
            logger.error("加载配置文件：" + TIP_PATH + "失败！");
            e.printStackTrace();
        }
    }

    public static Object getKey(String key) {
        return Tip.get(key);
    }

}
