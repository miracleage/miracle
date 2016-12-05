package com.miracle.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Created by yangchao2014 on 2016/12/2.
 */
public class ConfigUtil {
  static Properties config = null;

  public static String getConfig(String name) {
    if (null == config) {
      InputStream is = ConfigUtil.class.getResourceAsStream("/config.properties");
      config = new Properties();
      try {
        config.load(is);
      } catch (IOException e) {
        return null;
      }
    }
    return config.getProperty(name);
  }

  public static String getConfig(String properties, String name) {
    if (null == config) {
      InputStream is = ConfigUtil.class.getResourceAsStream("/" + properties);
      config = new Properties();
      try {
        config.load(is);
      } catch (IOException e) {
        return null;
      }
    }
    return config.getProperty(name);
  }
}
