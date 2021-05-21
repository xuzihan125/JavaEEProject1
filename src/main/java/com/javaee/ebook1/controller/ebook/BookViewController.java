package com.javaee.ebook1.controller.ebook;

import com.javaee.ebook1.common.JsonMessage;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.vo.BookViewVO;
import com.javaee.ebook1.service.BookViewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/7
 **/
@Controller
public class BookViewController {

    @Resource
    private BookViewService bookViewService;

    @ApiOperation(value="方法描述：获得图书单页，请求方法：GET，参数：bid,page，返回值：JsonMessage<BookViewVO>")
    @RequestMapping(value = "/user/bookView",method = RequestMethod.GET)
    public JsonMessage<BookViewVO> getBookView(@RequestParam String bid, @RequestParam int page) throws OpException {
        return new JsonMessage(bookViewService.getBookView(bid,page));
    }
}
