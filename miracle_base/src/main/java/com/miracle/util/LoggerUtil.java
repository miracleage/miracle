package com.miracle.util;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
 * Created by yangchao2014 on 2016/11/29.
 */
public class LoggerUtil {

  /** 记录调用外部系统接口的Logger */
  public static void inOutInfo(Object msg) {
    Logger.getLogger("inOutLogger").info(msg);
  }

  /** 登录模块的Logger */
  public static void loginInfo(Object msg) {
    Logger.getLogger("loginLogger").info(msg);
  }

  /** debugLogger */
  public static void debug(Object msg) {
    Logger.getLogger("debugLogger").debug(msg);
  }

  /** debugInfo */
  public static void debugInfo(Object msg) {
    Logger.getLogger("debugLogger").info(msg);
  }

  /** errorLogger */
  public static void error(String msg, Exception e) {
    printError(Logger.getLogger("errorLogger"), msg, e);
  }

  /** errorInfoLogger */
  public static void errorInfo(String msg, Exception e) {
    printError(Logger.getLogger("errorInfoLogger"), msg, e);
  }

  /** 需人工干预的报警logger */
  public static void alarmInfo(Object msg) {
    Logger.getLogger("alarmLogger").info(msg);
  }

  public static void printError(Logger logger, String msg, Exception e) {
    if (e == null) {
      logger.error(msg);
    } else {
      StringWriter sw = new StringWriter();
      PrintWriter ps = new PrintWriter(sw);
      ps.println(msg);
      e.printStackTrace(ps);
      logger.error(sw.toString());
    }
  }
}


