package com.smart.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Load properties util
 */
public class PropertiseUtil {

    private static final Logger logger = Logger.getLogger(Properties.class);
    private static Properties properties = new Properties();

    /**
     * get properties
     * @return properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * load .properties file
     * @param path
     * @return propeties
     */
    public static Properties load(String path) {
        properties.clear();
        File file = new File(path);
        if (file.canRead()) {
            try {
                properties.load(new FileInputStream(file));
            } catch (IOException e) {
                logger.info("load file which path is : (" + path +") failed!");
            }
        }
        return properties;
    }
}
