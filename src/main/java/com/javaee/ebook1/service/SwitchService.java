package com.javaee.ebook1.service;

import com.javaee.ebook1.common.exception.OpException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

public interface SwitchService {
    public ModelAndView switchView(HttpServletRequest request) throws OpException;

}
