package com.miracle.util;

import com.miracle.constant.CommonConstant;
import java.io.PrintWriter;
import java.io.StringWriter;
/*
 * Created by yangchao2014 on 2016/11/28.
 */
public class ExceptionUtil {
    /**
     * 将Exception 的 stack 跟踪信息 转换成Str
     */
    public static String parse(StackTraceElement[] e) {
        StringBuffer sb = new StringBuffer();
        int len = e.length;
        for (int i = 0; i < len; i++) {
            sb.append(e[i].toString());
            if (i < len) sb.append(CommonConstant.LINE_SEPARATOR);
        }
        return sb.toString();
    }

    /**
     * 将Exception 的 stack 跟踪信息 转换成Str
     */
    public static String parse(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    /**
     * 将Exception 的 stack 跟踪信息 转换成Str
     */
    public static String parse(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
