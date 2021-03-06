package com.javaee.ebook1.common.exception;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.JsonMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDeclarationException;

/**
 * @author xuzihan
 * @version 1.0
 * @description: 异常处理
 * @data 2021/3/30
 **/
@ControllerAdvice
public class ExceptionHandle {
    Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        logger.debug("建立连接");
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        logger.debug("结束连接");
        //model.addAttribute("author", "嘟嘟MD");
    }

    @ExceptionHandler(value = ConstraintDeclarationException.class)
    public Object handleConstraintDeclarationException(Exception e, HttpServletRequest req){
        logger.error(e.getMessage());
        e.printStackTrace();
        return new JsonMessage<String>(e.getMessage(),ResultCode.INVALID_ATTRIBUTE.getCode(),ResultCode.INVALID_ATTRIBUTE.getDesc());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("errorPage");
//        //业务异常
//        modelAndView.addObject("type","参数错误");
//        modelAndView.addObject("code", ();
//        return modelAndView;
    }

    @ExceptionHandler(value = OpException.class)
    public Object handleException(OpException e, HttpServletRequest req) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return new JsonMessage<String>("系统错误",e.getErrorCode(),e.getMessage());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("errorPage");
//        //业务异常
//        modelAndView.addObject("type", "程序错误");
//        modelAndView.addObject("code", ((OpException) e).getErrorCode());
//        return modelAndView;
    }

}
