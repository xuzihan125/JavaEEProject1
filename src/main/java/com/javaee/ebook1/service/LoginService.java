package com.javaee.ebook1.service;

import com.javaee.ebook1.mybatis.vo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface LoginService {// extends UserDetailsService {
//    boolean checkLogin(String mailbox, String password);

    @Validated
    ModelAndView checkLogin(@Valid UserVO userVO, HttpSession session);

    @Validated
    ModelAndView regist(@Valid UserVO userVO);

    ModelAndView logout(HttpSession session);
}
