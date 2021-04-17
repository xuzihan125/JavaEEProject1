package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookVO;
import com.javaee.ebook1.service.FileService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceImplTest {

    @Autowired
    FileService fileservice;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();

    }

    @Test
    void getPDF() {
    }

    @Test
    void getLog() {
    }

    @Test
    void addBook() throws OpException {
        BookVO book = new BookVO();
        book.setDescr("aa");
        book.setBookName("bb");
        book.setBid(1);
        book.setAuthor("CC");

        fileservice.addBook(book, request);
    }
}