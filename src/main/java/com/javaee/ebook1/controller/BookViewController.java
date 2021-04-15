package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.service.BookViewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/7
 **/
@Controller
public class BookViewController {

    @Resource
    private BookViewService bookViewService;

    @RequestMapping(value = "/user/bookView")
    public ModelAndView getBookView(@RequestParam String bid, @RequestParam int page) throws OpException {
        return bookViewService.getBookView(bid,page);
    }
}
