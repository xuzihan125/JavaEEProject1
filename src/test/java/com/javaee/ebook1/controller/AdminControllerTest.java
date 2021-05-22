package com.javaee.ebook1.controller;

import com.javaee.ebook1.controller.View.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminControllerTest {

    @Resource
    AdminController controller;

    @Test
    void getMainViewTest() {
        System.out.println("Test AdminControllerTest");
        ModelAndView mav = controller.getMainView();
        assertEquals(mav.getViewName(), "admin_main");
        System.out.println("The ModeelAndView's View name is : " + mav.getViewName());
    }
}