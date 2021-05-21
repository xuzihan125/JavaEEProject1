package com.javaee.ebook1.service;

import com.javaee.ebook1.mybatis.dto.BookListDTO;
import com.javaee.ebook1.mybatis.vo.BookListVO;
import org.springframework.web.servlet.ModelAndView;

public interface BookListService {

    BookListVO getBookList(BookListDTO bookListVO);

//    ModelAndView getBookList();
}
