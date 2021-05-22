package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.mybatis.dao.SysUserMapper;
import com.javaee.ebook1.mybatis.dao.UserRoleMapper;
import com.javaee.ebook1.mybatis.daoExt.RoleMapperExt;
import com.javaee.ebook1.mybatis.vo.LoginVO;
import com.javaee.ebook1.mybatis.vo.UserVO;
import com.javaee.ebook1.service.BookListService;
import com.javaee.ebook1.service.LoginService;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.websocket.Session;

import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    LoginService loginservice;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;

    @BeforeEach
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
        session = new MockHttpSession();

    }

    @Test
    void checkLoginTest() {
        UserVO userVO = new UserVO();
        userVO.setEmailAddress("aaa@aaa.com");
        userVO.setPassword("123456");
        userVO.setNickname("aaa");

        if(session == null){
            System.out.println("Session is null");
        }

        LoginVO mav = loginservice.checkLogin(userVO);
        //assertEquals(mav.getViewName(), "login");
        assertEquals(session.getAttribute("nickname"), "aaa");
    }

    @Test
    void logoutTest() {

    }

    @Test
    @Transactional
    @Rollback
    void regist() {
    }
}