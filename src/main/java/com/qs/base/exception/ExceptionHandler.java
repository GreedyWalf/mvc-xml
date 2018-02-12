package com.qs.base.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一错误处理
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Map<String, Object> errMap = new HashMap<String, Object>();
        errMap.put("msg", e.getMessage());
        e.printStackTrace();

        return new ModelAndView("error/error", errMap);
    }
}
