package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.common.util.FileUtil;
import com.javaee.ebook1.mybatis.dao.BooksMapper;
import com.javaee.ebook1.mybatis.entity.Books;
import com.javaee.ebook1.mybatis.entity.BooksExample;
import com.javaee.ebook1.mybatis.vo.BookViewVO;
import com.javaee.ebook1.service.BookViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/13
 **/
@Service
public class BookViewServiceImpl implements BookViewService {
    @Resource
    private BooksMapper booksMapper;

    @Resource
    private FileUtil fileUtil;

    /**
     * @description: 未完成
     * @author xuzih
     * @date 2021/4/13 19:58
     * @version 1.0
     */
    @Override
    public BookViewVO getBookView(String bid, Integer page){
        //获得书籍参数
        BooksExample example = new BooksExample();
        example.createCriteria().andBidEqualTo(Integer.valueOf(bid));
        List<Books> result = booksMapper.selectByExample(example);
        Books book = result.stream().findFirst().orElse(null);
        if(book == null ){
            throw new OpException(ResultCode.NOT_EXIST_BOOK.getDesc(),ResultCode.NOT_EXIST_BOOK.getCode());
        }
        if(book.getPage()>page){
            throw new OpException(ResultCode.INVALID_INPUT.getDesc(),ResultCode.INVALID_INPUT.getDesc());
        }
        //检查文件是否存在
        String folder = fileUtil.absolutePath+"image/content/"+book.getFolder();
        File folderFile = new File(folder);
        if(!folderFile.exists()){
            fileUtil.generateImage(fileUtil.absolutePath+"book/"+book.getFile(),folder);
        }
        //创建返回参数
        BookViewVO bookViewVO = new BookViewVO();
        bookViewVO.setBookName(book.getBookName());
        bookViewVO.setImgUrl(book.getFolder()+"/"+page+".png");
        bookViewVO.setTotalPage(book.getPage());
        return bookViewVO;
    }
}
