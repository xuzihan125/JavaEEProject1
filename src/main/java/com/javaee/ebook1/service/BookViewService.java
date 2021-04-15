package com.javaee.ebook1.service;

import com.javaee.ebook1.common.exception.OpException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/13
 **/
public interface BookViewService {
    ModelAndView getBookView(String uid,Integer page) throws OpException;
}
