package com.javaee.ebook1.controller;

import com.javaee.ebook1.controller.ebook.BooklistController;
import com.javaee.ebook1.mybatis.dto.BookListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

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
//        ModelAndView mav = controller.GetBookList();
    }

    @Test
    void testGetBookList() throws Exception {
        System.out.println("Test getBookList(BookListDTO)");
        BookListDTO booklist = new BookListDTO("abcde","12412", 1, 1);

//        ModelAndView mav = controller.GetBookList(booklist, mockBindingResult);
//        System.out.println(mav.toString());
    }
}