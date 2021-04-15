package com.javaee.ebook1.service;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookVO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void getPDF(String file, HttpServletResponse response) throws OpException;

    ModelAndView getLog(String type)throws OpException;

    ModelAndView addBook(BookVO bookVO, HttpServletRequest request) throws OpException;

}
