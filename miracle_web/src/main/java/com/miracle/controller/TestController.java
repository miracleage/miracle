package com.miracle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Created by yangchao2014 on 2016/11/30.
 */
@RestController
public class TestController {

    @RequestMapping("/test.do")
    public String test(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp,
            String nonce, String echostr) {
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        return echostr;
    }
}
