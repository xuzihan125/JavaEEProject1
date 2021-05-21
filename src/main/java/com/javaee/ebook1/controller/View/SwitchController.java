package com.javaee.ebook1.controller.View;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.service.BookViewService;
import com.javaee.ebook1.service.SwitchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/14
 **/
@Controller
public class SwitchController {
    @Resource
    private SwitchService switchService;

    @RequestMapping(value = "/switch")
    public ModelAndView getBookView(HttpServletRequest request) throws OpException {
        return switchService.switchView(request);
    }
}
