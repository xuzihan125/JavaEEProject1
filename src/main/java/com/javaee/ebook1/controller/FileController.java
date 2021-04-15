package com.javaee.ebook1.controller;

import com.javaee.ebook1.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/7
 **/
@Controller
public class FileController {

    @Resource
    private FileService fileService;


    @RequestMapping(value = "/user/books/{file}",method = RequestMethod.GET)
    public void prePDF(@PathVariable String file, HttpServletResponse response) throws Exception{
        fileService.getPDF(file,response);
    }

}
