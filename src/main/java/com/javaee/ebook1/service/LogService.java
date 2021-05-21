package com.javaee.ebook1.service;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.LogVO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface LogService {
    LogVO getLog(String type);
}
