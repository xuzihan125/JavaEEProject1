package com.javaee.ebook1.service;

import com.javaee.ebook1.mybatis.entity.Books;
import com.javaee.ebook1.mybatis.vo.BookListVO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BookListService {

    ModelAndView getBookList(BookListVO bookListVO);

    ModelAndView getBookList();
}
