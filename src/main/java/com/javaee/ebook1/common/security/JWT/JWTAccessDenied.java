package com.javaee.ebook1.common.security.JWT;

import com.alibaba.fastjson.JSON;
import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.JsonMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/20
 **/
@Component
public class JWTAccessDenied implements AccessDeniedHandler  {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.getWriter()
                .write(JSON.toJSONString(
                        new JsonMessage<String>(e.getMessage(),
                                ResultCode.INVALID_AUTHOR.getCode(),
                                ResultCode.INVALID_AUTHOR.getDesc())));
    }
}
