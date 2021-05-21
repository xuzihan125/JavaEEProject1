package com.javaee.ebook1.service;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookVO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    String getPDF(String file, HttpServletResponse response) ;

    String addBook(BookVO bookVO, HttpServletRequest request);

}
