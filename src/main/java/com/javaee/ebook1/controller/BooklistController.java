package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookListVO;
import com.javaee.ebook1.service.BookListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value = "获得图书列表")
@Controller
public class BooklistController {
    @Resource
    private BookListService bookListService;


    @GetMapping(value = "/user/booksList")
    public ModelAndView GetBookList() throws Exception{
        return bookListService.getBookList();
    }

    @ApiOperation(value = "获得文件列表")
    @RequestMapping(value = "/user/booksList")
    public ModelAndView GetBookList(@Valid BookListVO bookListVO, BindingResult userCheckResult) throws Exception{
        if(userCheckResult.hasErrors()){
            throw new OpException(userCheckResult.getFieldError().getDefaultMessage(),ResultCode.INVALID_INPUT.getCode());
        }
        return bookListService.getBookList(bookListVO);
    }

}
