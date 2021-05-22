package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.controller.ebook.BookViewController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@SpringBootTest
class BookViewControllerTest {

    @Resource
    BookViewController controller;

    @Test
    void getBookView() throws OpException {
        System.out.println("Test BookViewController");
//        ModelAndView mav = controller.getBookView("27", 1);
//        System.out.println(mav.toString());
    }
}