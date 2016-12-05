package com.miracle.interceptor;

import com.miracle.service.EmailTaskService;
import com.miracle.util.ExceptionUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yangchao2014 on 2016/12/2.
 */
@Aspect
@Component
public class SendErrorMailInterceptor {

    @Autowired
    private EmailTaskService emailTaskService;

    @Before("execution(* com.miracle.util.LoggerUtil.error(..)) ")
    public void sendMailBeforeError(ProceedingJoinPoint point) {

        Object[] args = point.getArgs();
        String errorMsg = (String) args[0];
        Exception stackMsg = (Exception) args[1];
        InetAddress inet = null;
        try {
            inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String error = "本机IP：" + (null != inet ? inet.getHostAddress() : "UnknowHost") + "/r/n" +
                "异常信息：" + errorMsg + "/r/n" +
                "堆栈信息：" + ExceptionUtil.parse(stackMsg);
        String SUBJECT = "系统异常-miracle";
        String TO_ACCOUNT = "dlut_yangchao@163.com";
        emailTaskService.asynSend(TO_ACCOUNT, SUBJECT,error);
    }
}
