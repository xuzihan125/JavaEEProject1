package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.common.util.FileUtil;
import com.javaee.ebook1.mybatis.dao.BooksMapper;
import com.javaee.ebook1.mybatis.entity.Books;
import com.javaee.ebook1.mybatis.entity.BooksExample;
import com.javaee.ebook1.mybatis.vo.BookVO;
import com.javaee.ebook1.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/7
 **/
@Service
public class FileServiceImpl implements FileService {
    @Value("${absolutePath.resource}")
    private String absolutePath;

    @Value("${relativePath.pdf}")
    private String relativeBookPath;

    @Value("${relativePath.cover}")
    private String relativeCoverPath;

    @Value("${relativePath.content}")
    private String relativeContentPath;

    @Resource
    private BooksMapper booksMapper;

    @Resource
    private FileUtil fileUtil;

    /**
     * @description: pdf传输
     * @author xuzih
     * @date 2021/4/7 17:03
     * @version 1.0
     */
    @Override
    public String getPDF(String file, HttpServletResponse response) throws OpException {
        response.reset();
        response.setContentType("application/pdf");
        try{
            File pdfFile = new File(absolutePath+relativeBookPath+file);
            String tempo = pdfFile.getAbsolutePath();
            FileInputStream fileInputStream = new FileInputStream(pdfFile);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream),outputStream);
            response.setHeader("Content-Disposition","inline; filename="+ file);
            outputStream.flush();
        }catch(Exception e){
            throw new OpException(e.getMessage(),"000111");
        }
        return "文件上传成功";
    }

    public String addBook(BookVO bookVO, HttpServletRequest request) throws OpException{
        Books books = new Books();
        if(bookVO.getBid()==null){
            books.setAuthor(bookVO.getAuthor());
            books.setBookName(bookVO.getBookName());
            books.setDescr(bookVO.getDescr());
            booksMapper.insert(books);
        }
        else{
            books.setBid(books.getBid());
        }
        List<MultipartFile> cover = ((MultipartHttpServletRequest) request).getFiles("cover");
        List<MultipartFile> content = ((MultipartHttpServletRequest) request).getFiles("content");
        cover.addAll(content);
        BufferedOutputStream stream = null;
        try{
            for(MultipartFile file : cover){
                if(!file.isEmpty()){
                    String file_type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    String fileName = books.getBid() + file_type;
                    String path = null;
                    if(file_type.equals(".jpg") || file_type.equals(".png")){
                        path = absolutePath + relativeCoverPath + fileName;
                        books.setBookCover(fileName);
                    }
                    else if(file_type.equals(".pdf")){
                        path = absolutePath + relativeBookPath + fileName;
                        books.setFile(fileName);
                    }
                    else{
                        throw new RuntimeException();
                    }
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
                    stream.write(bytes);
                    stream.close();
                }
            }
        }catch (Exception e){
            BooksExample example = new BooksExample();
            example.createCriteria().andBidEqualTo(books.getBid());
            booksMapper.deleteByExample(example);
            throw new OpException(ResultCode.FILE_OPERATION_FAIL.getDesc(),ResultCode.FILE_OPERATION_FAIL.getCode());
        }
        int page = fileUtil.generateImage(absolutePath+relativeBookPath+books.getFile(),absolutePath+relativeContentPath+books.getBid());
        books.setAuthor(bookVO.getAuthor());
        books.setBookName(bookVO.getBookName());
        books.setDescr(bookVO.getDescr());
        books.setPage(page);
        books.setFolder(""+books.getBid());
        BooksExample example = new BooksExample();
        example.createCriteria().andBidEqualTo(books.getBid());
        booksMapper.updateByExample(books,example);
        return "上传成功";
    }

}
