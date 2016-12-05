package com.miracle.handler;

import com.alibaba.fastjson.JSON;
import com.miracle.constant.GlobalResponseConstant;
import com.miracle.util.LoggerUtil;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Created by yangchao2014 on 2016/11/29.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

  public static String getErrorBackUrl(String url) {
    return "";
  }

  @Override
  public ModelAndView resolveException(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) {
    LoggerUtil.error("url:" + httpServletRequest.getRequestURI() + ", parameterMap:"
        + JSON.toJSONString(httpServletRequest.getParameterMap()), e);

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("errorInfo", "网络拥堵，请稍后再试");
    modelAndView.addObject("url",
        ExceptionHandler.getErrorBackUrl(httpServletRequest.getRequestURI()));
    modelAndView.setViewName(GlobalResponseConstant.ERROR_PAGE);
    return modelAndView;
  }
}
