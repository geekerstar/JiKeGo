package com.jikego.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/7/23 15:00
 * @Description: 全局异常处理类, 防止代码及数据库信息泄露
 */
@Slf4j
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    /**
     * @description: 处理异常
     *
     * @auther: geekerstar
     * @date: 2018/12/27 16:05
     * @param: [httpServletRequest, httpServletResponse, o, e]
     * @return: org.springframework.web.servlet.ModelAndView
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.error("{} Exception", httpServletRequest.getRequestURI(), e);
        ModelAndView modelAndView = new ModelAndView(new MappingJacksonJsonView());

        //当使用是jackson2.x的时候使用MappingJackson2JsonView，目前使用的是1.9。
        modelAndView.addObject("status", ResponseCode.ERROR.getCode());
        modelAndView.addObject("msg", "接口异常,详情请查看服务端日志的异常信息");
        modelAndView.addObject("data", e.toString());
        return modelAndView;
    }
}

