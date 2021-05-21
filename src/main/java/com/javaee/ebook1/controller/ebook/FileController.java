package com.javaee.ebook1.controller.ebook;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.JsonMessage;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookVO;
import com.javaee.ebook1.mybatis.vo.UserVO;
import com.javaee.ebook1.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/7
 **/
@Api(value = "文件上传")
@Controller
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation(value="方法描述：获得图书单页，请求方法：GET，参数：file，返回值：JsonMessage<String>")
    @RequestMapping(value = "/user/books/{file}",method = RequestMethod.GET)
    public JsonMessage<String> prePDF(@PathVariable String file, HttpServletResponse response) throws Exception{
        return new JsonMessage<String>(fileService.getPDF(file,response));
    }

    @GetMapping(value = "/admin/upload")
    public ModelAndView getUploadPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_upload");
        return modelAndView;
    }

    @PostMapping(value = "/admin/upload")
    public JsonMessage<String> addBook(@Valid BookVO bookVO, HttpServletRequest request, BindingResult bindingResult) throws OpException {
        if(bindingResult.hasErrors()){
            throw new OpException(ResultCode.INVALID_INPUT.getDesc(),ResultCode.INVALID_INPUT.getCode());
        }
        return new JsonMessage<String>(fileService.addBook(bookVO,request));
    }

}
