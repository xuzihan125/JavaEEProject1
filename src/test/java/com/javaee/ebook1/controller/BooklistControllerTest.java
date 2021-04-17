package com.javaee.ebook1.controller;

import com.javaee.ebook1.mybatis.vo.BookListVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BooklistControllerTest {

    @Resource
    BooklistController controller;

    @MockBean
    private BindingResult mockBindingResult;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void start(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getBookList() throws Exception {
        System.out.println("Test getBookList()");
        ModelAndView mav = controller.GetBookList();
    }

    @Test
    void testGetBookList() throws Exception {
        System.out.println("Test getBookList(BookListVO)");
        BookListVO booklist = new BookListVO("abcde","12412", 1, 1);

        ModelAndView mav = controller.GetBookList(booklist, mockBindingResult);
        System.out.println(mav.toString());
    }
}