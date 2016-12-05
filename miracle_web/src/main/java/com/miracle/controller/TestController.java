package com.miracle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Created by yangchao2014 on 2016/11/30.
 */
@Controller
public class TestController {

    @RequestMapping("/test.do")
    public String test(HttpServletRequest request, HttpServletResponse response){
        return "test";
    }
}
