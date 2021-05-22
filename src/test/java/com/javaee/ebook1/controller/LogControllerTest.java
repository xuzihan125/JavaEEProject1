package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.JsonMessage;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.controller.admin.LogController;
import com.javaee.ebook1.mybatis.vo.LogVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class LogControllerTest {

    @Resource
    LogController controller;

    @Test
    void getLog() {
        System.out.println("Test LogController Info Log");
        JsonMessage<LogVO> mav = controller.getLog("info");
        System.out.println(mav.toString());
    }
}