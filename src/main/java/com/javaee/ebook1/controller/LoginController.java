package com.javaee.ebook1.controller;

import com.javaee.ebook1.mybatis.vo.UserVO;
import com.javaee.ebook1.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/3/30
 **/
@Controller
public class LoginController {
    @Resource
    private LoginService loginService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public String Login(Model model){
        logger.info("进入登录界面");
        model.addAttribute("logout",false);
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView Login(ModelAndView modelAndView, @Valid UserVO user, BindingResult userCheckResult, HttpSession session){
        logger.info("尝试登录");
        if(userCheckResult.hasErrors()){
            logger.info("登录参数错误");
            modelAndView.setViewName("login");
            modelAndView.addObject("error",userCheckResult.getFieldError().getDefaultMessage());
            return modelAndView;
        }
        return loginService.checkLogin(user,session);
    }

    @GetMapping(value = "/regist")
    public String regist(Model model){
        logger.info("进入注册界面");
        model.addAttribute("userVO",new UserVO());
        return "regist";
    }

    @PostMapping(value = "/regist")
    public ModelAndView regist(@Valid UserVO user,BindingResult userCheckResult){
        logger.info("尝试注册");
        if(userCheckResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView();
            logger.info("注册参数错误");
            modelAndView.setViewName("regist");
            modelAndView.addObject("error",userCheckResult.getFieldError().getDefaultMessage());
            modelAndView.addObject("userVO",user);
            return modelAndView;
        }
        return loginService.regist(user);
    }

//    @PostMapping(value = "/regist")
//    public String regist(Model model){
//        logger.info("进入注册界面");
//        return "login";
//    }

    @RequestMapping(value = "/logout")
    public ModelAndView Logout(Model model,HttpSession session){
        logger.info("登出");
        return loginService.logout(session);
    }


}
