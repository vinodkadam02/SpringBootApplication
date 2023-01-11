package com.elixr.poc.util;

import lombok.extern.log4j.Log4j2;
import org.apache.catalina.core.Constants;

import java.util.Properties;

@Log4j2
public class PropertiesUtil {
    private static final String PATH = "/messages.properties";
    private static Properties properties;
    private static String value;

    public static PropertiesUtil getInstance()
    {
        return new PropertiesUtil();
    }

    private void init() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(Constants.class.getResourceAsStream(PATH));
            }
            catch (Exception e) {
                log.error("Unable to load " + PATH + " file from classpath.", e);
                System.exit(1);
            }
        }
        value = (String) properties.get(this.toString());
    }

    public String getValue() {
        if (value == null) {
            init();
        }
        return value;
    }
}
