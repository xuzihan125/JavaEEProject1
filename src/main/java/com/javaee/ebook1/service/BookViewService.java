package com.javaee.ebook1.service;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookViewVO;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/13
 **/
public interface BookViewService {
    BookViewVO getBookView(String uid, Integer page);
}
