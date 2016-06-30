package com.teamtreehouse.web;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReferrerInterceptor extends HandlerInterceptorAdapter {
    public static final String ATT_REFERRER_URI = "referrer";
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        request.getSession().setAttribute(ATT_REFERRER_URI,request.getRequestURI());
    }

    public static String redirect(HttpServletRequest req) {
        return "redirect:" + req.getSession().getAttribute(ATT_REFERRER_URI);
    }
}