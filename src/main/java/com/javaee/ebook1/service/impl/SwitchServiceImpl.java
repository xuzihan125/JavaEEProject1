package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.Enum.RoleEnum;
import com.javaee.ebook1.common.Enum.SessionAttribute;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.service.SwitchService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/14
 **/
@Service
public class SwitchServiceImpl implements SwitchService {
    @Override
    public ModelAndView switchView(HttpServletRequest request) throws OpException {
        List<String> roles = (List<String>)request.getSession().getAttribute(SessionAttribute.ROLE.getCode());
        boolean user = roles.stream().anyMatch(role-> role.equals(RoleEnum.NORMAL_READER.getRole()));
        boolean admin = roles.stream().anyMatch(role-> role.equals(RoleEnum.ADMIN.getRole()));
        ModelAndView modelAndView = new ModelAndView();
        if(user && admin){
            modelAndView.setViewName("switch");
        }
        else if(user){
            modelAndView.setViewName("redirect:/user/booksList");
        }
        else if(admin){
            modelAndView.setViewName("redirect:/admin/main");
        }
        else{
            throw new OpException(ResultCode.INVALID_AUTHOR.getDesc(),ResultCode.INVALID_AUTHOR.getCode());
        }

        return modelAndView;
    }
}
