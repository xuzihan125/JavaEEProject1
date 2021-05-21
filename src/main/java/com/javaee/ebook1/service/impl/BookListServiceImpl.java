package com.javaee.ebook1.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaee.ebook1.common.util.ExampleUtil;
import com.javaee.ebook1.mybatis.dao.BooksMapper;
import com.javaee.ebook1.mybatis.dto.BookListDTO;
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

//    @Override
//    public ModelAndView getBookList(){
//        BookListDTO bookListDTO = new BookListDTO("","",1,10);
//        return getBookList(bookListVO);
//    }

    @Override
    @Validated
    public BookListVO getBookList(@Valid BookListDTO bookListDTO) {
        //获取参数
        String name = bookListDTO.getName()==null?"":bookListDTO.getName();
        String author = bookListDTO.getAuthor()==null?"":bookListDTO.getAuthor();
        int pageNo = bookListDTO.getPageNo();
        int pageSize = bookListDTO.getPageSize();
        //查询
        BooksExample example = new BooksExample();
        example.createCriteria().andBookNameLike(ExampleUtil.likeModify(name)).andAuthorLike(ExampleUtil.likeModify(author));
        PageHelper.startPage(pageNo,pageSize);
        List<Books> result = booksMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(result);
        int totalPage = pageInfo.getPages();
        boolean next = pageInfo.isHasNextPage();
        boolean prev = pageInfo.isHasPreviousPage();
        //创建返回参数
        BookListVO bookListVO = new BookListVO();
        bookListVO.setBooksList(result);
        bookListVO.setHasNext(next);
        bookListVO.setHasPrev(prev);
        return bookListVO;
    }
}
