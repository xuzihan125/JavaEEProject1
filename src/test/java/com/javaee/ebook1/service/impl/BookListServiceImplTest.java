package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.mybatis.vo.BookListVO;
import com.javaee.ebook1.service.BookListService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookListServiceImplTest {

    @Autowired
    BookListService bls;

    @AfterAll
    static void end(){
        System.out.println("All Book List Service Test Done.");
    }

    @Test
    void getBookList() {
        ModelAndView mav = bls.getBookList();

        assertEquals(mav.getViewName(), "booksList");
        System.out.println("getBookList() Done.");
    }

    @Test
    void testGetBookList() {
        BookListVO booklist = new BookListVO("1","1",1,0);

        ModelAndView mav = bls.getBookList(booklist);
        assertEquals(mav.getViewName(), "booksList");
        System.out.println("getBookList(BookListVO) Done.");
    }
}