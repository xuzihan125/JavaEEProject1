package com.javaee.ebook1.common.security;

import com.javaee.ebook1.common.Enum.RoleEnum;
import com.javaee.ebook1.common.Enum.SessionAttribute;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author xuzihan
 * @version 1.0
 * @description: 登陆拦截器（已停用）
 * @data 2021/4/2
 **/
@Component
public class UserIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<String> roles = (List<String>)request.getSession().getAttribute(SessionAttribute.ROLE.getCode());
        if(roles == null){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        String getRole = roles.stream().filter(role->role.equals(RoleEnum.NORMAL_READER.getRole())).findFirst().orElse(null);
        if(getRole==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
