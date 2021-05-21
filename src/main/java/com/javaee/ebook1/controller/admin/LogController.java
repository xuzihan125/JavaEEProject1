package com.javaee.ebook1.controller.admin;

import com.javaee.ebook1.common.JsonMessage;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.LogVO;
import com.javaee.ebook1.service.FileService;
import com.javaee.ebook1.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/15
 **/
@Api(value = "日志接口")
@RestController
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping(value = "/admin/log", method = RequestMethod.GET)
    @ApiOperation(value = "方法描述：获得日志，请求方法：GET，参数：String，返回值：JsonMessage<LogVO>")
    public JsonMessage<LogVO> getLog(@RequestParam String type) throws OpException {
        return new JsonMessage<>(logService.getLog(type));
    }
}
