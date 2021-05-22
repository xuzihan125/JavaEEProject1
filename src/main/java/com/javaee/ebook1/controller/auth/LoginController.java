package com.javaee.ebook1.controller.auth;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.JsonMessage;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.common.security.JWT.JWTUtil;
import com.javaee.ebook1.mybatis.vo.LoginVO;
import com.javaee.ebook1.mybatis.vo.RegistVO;
import com.javaee.ebook1.mybatis.vo.UserVO;
import com.javaee.ebook1.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/3/30
 **/
@RestController
@Api(value = "登录类", tags = "用户操作接口")
public class LoginController {
    @Resource
    private LoginService loginService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value="方法描述：原界面接口，现弃用。请求方法：GET，参数：无，返回值：JsonMessage<String>,是否可用：是/否")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JsonMessage<String> Login(){
        return new JsonMessage<>("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="方法描述：用户登录，请求方法：GET，参数：String，返回值：JsonMessage<LogVO>")
    public JsonMessage<LoginVO> Login(@Valid UserVO user, BindingResult userCheckResult){
        logger.info("尝试登录");
        if(userCheckResult.hasErrors()){
            logger.info("登录参数错误");
            throw new OpException(ResultCode.INVALID_ATTRIBUTE.getDesc(),ResultCode.INVALID_ATTRIBUTE.getCode());
        }
        return new JsonMessage<>(loginService.checkLogin(user));
    }

    @GetMapping(value = "/regist")
    @ApiOperation(value="方法描述：原界面接口，现弃用。请求方法：GET，参数：无，返回值：JsonMessage<String>")
    public JsonMessage<String> regist(Model model){
        return new JsonMessage<String>("regist");
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ApiOperation(value="方法描述：用户注册。请求方法：POST，参数：UserVO，返回值：JsonMessage<String>")
    public JsonMessage<RegistVO> regist(@Valid UserVO user, BindingResult userCheckResult){
        logger.info("尝试注册");
        if(userCheckResult.hasErrors()){
            logger.info("注册参数错误");
            throw new OpException(ResultCode.INVALID_ATTRIBUTE.getDesc(),ResultCode.INVALID_ATTRIBUTE.getCode());
        }
        return new JsonMessage(loginService.regist(user));
    }

    @ApiOperation(value="方法描述：原登出接口，现弃用。请求方法：POST，参数：UserVO，返回值：JsonMessage<String>")
    @RequestMapping(value = "/logout",method = RequestMethod.DELETE)
    public JsonMessage<String> Logout(Model model,HttpSession session){
        logger.info("登出");
        return new JsonMessage<String>("登出");
    }

}
