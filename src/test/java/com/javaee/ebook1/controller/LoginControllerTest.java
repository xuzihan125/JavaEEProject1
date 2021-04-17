package com.javaee.ebook1.controller;

import com.javaee.ebook1.mybatis.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.ebook1.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
class LoginControllerTest {

    @Resource
    private MockHttpServletRequest request;

    @Resource
    private MockHttpServletResponse response;

    @Resource
    private MockHttpSession session;

    @MockBean
    private BindingResult mockBindingResult;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Resource
    LoginController loginController;

    private MockMvc mockMvc;

    @BeforeEach
    public void start(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
    }

    @Test
    void loginTest() {
        UserVO userVO = new UserVO();
        userVO.setEmailAddress("aaa@aaa.com");
        userVO.setPassword("123456");
        userVO.setNickname("aaa");

        if(session == null){
            System.out.println("Session is null");
        }

        ModelAndView mav = loginController.Login(null, userVO, mockBindingResult, session);
        assertEquals(session.getAttribute("nickname"), "aaa");
        System.out.println("Login Successfully!");
    }

    @Test
    void regist() {
        UserVO userVO = new UserVO();
        userVO.setEmailAddress("new_user@aaa.com");
        userVO.setPassword("123456");
        userVO.setNickname("new_user");

        ModelAndView mav = loginController.regist(userVO, mockBindingResult);
        assertEquals(mav.getViewName(), "regist");
        System.out.println("Register successfully!");
    }

    @Test
    void logout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/logout"))
                .andDo(print())
                .andExpect(status().is(302));
        System.out.println("Logout Successfully!");
    }
}