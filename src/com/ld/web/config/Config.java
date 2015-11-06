package com.ld.web.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.ld.web.action.BaseAction;
import com.ld.web.enumeration.ExceptionType;

/**
 * 
 *<p>Title: Config</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 系统配置</p>
 *
 *@author LD
 *
 *@date 2015-10-28
 */
public class Config {

    private static final Logger logger = Logger.getLogger(BaseAction.class);

    private static final String CONFIG_FILE = "config.properties";

    private static final String CONFIG_DEBUG = "debug";

    public static Boolean debug;

    static {
        try {
            Properties config = new Properties();
            config.load(Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
            logger.info("Load config.properties begin...");
            debug = Boolean.valueOf(config.getProperty(CONFIG_DEBUG));

            logger.info("Load config.properties success...");
        } catch (IOException e) {
            MDC.put(ExceptionType.EXCEPTION_TYPE, ExceptionType.SYSTEM);
            logger.error(String.format("Load config error by: %s", e.getMessage()), e);
        }
    }
}
