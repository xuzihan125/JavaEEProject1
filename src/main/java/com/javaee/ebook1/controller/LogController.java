package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/15
 **/
@Controller
public class LogController {

    @Resource
    private FileService fileService;

    @RequestMapping("/admin/log")
    public ModelAndView getLog(@RequestParam String type) throws OpException {
        return fileService.getLog(type);
    }
}
