package com.javaee.ebook1.controller.ebook;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.JsonMessage;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.dto.BookListDTO;
import com.javaee.ebook1.mybatis.vo.BookListVO;
import com.javaee.ebook1.service.BookListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value = "获得图书列表")
@RestController
public class BooklistController {
    @Resource
    private BookListService bookListService;


//    @GetMapping(value = "/user/booksList")
//    public ModelAndView GetBookList() throws Exception{
//        return bookListService.getBookList();
//    }

    @ApiOperation(value="方法描述：获得图书列表，请求方法：GET，参数：bookListVO，返回值：JsonMessage<LogVO>")
    @RequestMapping(value = "/user/booksList", method = RequestMethod.GET)
    public JsonMessage<BookListVO> GetBookList(@Valid BookListDTO bookListVO, BindingResult userCheckResult) throws Exception{
        if(userCheckResult.hasErrors()){
            throw new OpException(userCheckResult.getFieldError().getDefaultMessage(),ResultCode.INVALID_INPUT.getCode());
        }
        return new JsonMessage(bookListService.getBookList(bookListVO));
    }

}
