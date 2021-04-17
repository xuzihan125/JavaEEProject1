package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.exception.OpException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogControllerTest {

    @Resource
    LogController controller;

    @Test
    void getLog() throws OpException {
        System.out.println("Test LogController Info Log");
        ModelAndView mav = controller.getLog("info");
        System.out.println(mav.toString());
    }
}