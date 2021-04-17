package com.javaee.ebook1.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.ebook1.common.util.ExampleUtil;
import com.javaee.ebook1.mybatis.dao.BooksMapper;
import com.javaee.ebook1.mybatis.entity.Books;
import com.javaee.ebook1.mybatis.entity.BooksExample;
import com.javaee.ebook1.mybatis.vo.BookListVO;
import com.javaee.ebook1.service.BookListService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/3/30
 **/
@Service
public class BookListServiceImpl implements BookListService {
    @Resource
    private BooksMapper booksMapper;

    @Override
    public ModelAndView getBookList(){
        BookListVO bookListVO = new BookListVO("","",1,10);
        return getBookList(bookListVO);
    }

    @Override
    @Validated
    public ModelAndView getBookList(@Valid BookListVO bookListVO) {
        //获取参数
        String name = bookListVO.getName()==null?"":bookListVO.getName();
        String author = bookListVO.getAuthor()==null?"":bookListVO.getAuthor();
        int pageNo = bookListVO.getPageNo();
        int pageSize = bookListVO.getPageSize();
        //查询
        BooksExample example = new BooksExample();
        example.createCriteria().andBookNameLike(ExampleUtil.likeModify(name)).andAuthorLike(ExampleUtil.likeModify(author));
        PageHelper.startPage(pageNo,pageSize);
        List<Books> result = booksMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(result);
        int totalPage = pageInfo.getPages();
        boolean next = pageInfo.isHasNextPage();
        boolean prev = pageInfo.isHasPreviousPage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booksList");
        modelAndView.addObject("books",result);
        modelAndView.addObject("name",name);
        modelAndView.addObject("author",author);
        modelAndView.addObject("pageNo",pageNo);
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("totalPage",totalPage);
        modelAndView.addObject("next",next);
        modelAndView.addObject("prev",prev);

        return modelAndView;
    }
}
