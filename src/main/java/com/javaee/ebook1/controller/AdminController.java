package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.exception.OpException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/14
 **/
@Controller
public class AdminController {

    @RequestMapping(value = "/admin/main")
    public ModelAndView getMainView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_main");
        return modelAndView;
    }
}
