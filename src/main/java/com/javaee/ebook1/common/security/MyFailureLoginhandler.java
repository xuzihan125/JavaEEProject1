package com.javaee.ebook1.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/10
 **/
//@Component
//public class MyFailureLoginhandler extends SimpleUrlAuthenticationFailureHandler {
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        response.setContentType("application/json;charset=UTF-8");
//        Map<String,String> map = new HashMap<>();
//        map.put("error",exception.getMessage());
//        response.getWriter().write(objectMapper.writeValueAsString(map));
//        super.onAuthenticationFailure(request, response, exception);
//    }
//}
