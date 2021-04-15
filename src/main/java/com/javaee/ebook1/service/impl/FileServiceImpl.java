package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


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

    @Value("${relativePath.log}")
    private String relativeLogPath;

    /**
     * @description: pdf传输
     * @author xuzih
     * @date 2021/4/7 17:03
     * @version 1.0
     */
    @Override
    public void getPDF(String file, HttpServletResponse response) throws OpException {
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
    }

    @Override
    public ModelAndView getLog(String type) throws OpException{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_log");
        modelAndView.addObject("type",type);
        try{
            String file_name = type+".log";
            File logFile = new File(absolutePath+relativeLogPath+file_name);
            BufferedReader fileReadBuffer = new BufferedReader(new FileReader(logFile));
            List<String> content = new ArrayList<>();
            String read = fileReadBuffer.readLine();
            while(read!=null){
                content.add(read+"\n");
                read = fileReadBuffer.readLine();
            }
            fileReadBuffer.close();
            modelAndView.addObject("log",content);
        }catch(Exception e){
            throw new OpException(e.getMessage(),"000111");
        }
        return modelAndView;
    }


}
