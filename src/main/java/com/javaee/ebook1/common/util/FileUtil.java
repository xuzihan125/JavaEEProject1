package com.javaee.ebook1.common.util;



import com.itextpdf.text.pdf.PdfReader;
import com.javaee.ebook1.common.exception.OpException;
import com.sun.xml.internal.txw2.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/3
 **/
@Component
public class FileUtil {

    @Value("${absolutePath.resource}")
    public String absolutePath;

    /**
     * @description: 获得resource内的文件的路径(待修改)
     * @author xuzih
     * @date 2021/4/3 19:51
     * @version 1.0
     */
    public String getFileSourceFromResource(String path) {
        String realPath = null;
        ClassLoader classLoader = getClass().getClassLoader();
        if (path != null && !path.equals("")) {
            realPath = classLoader.getResource(path).getPath();
        }
        return realPath;
    }

    /**
     * @param dpi          清晰度
     * @param filePath     文件路径
     * @return 转换后页数
     * @description: PDF转图片
     * @author xuzih
     * @date 2021/4/7 17:48
     * @version 1.0
     */
    public int generateImage(String filePath, String imgDirPath, int dpi) throws OpException {
        File file = new File(filePath);
        PDDocument pdDocument;
        int pages = 0;
        try {
            File imgDirFile = new File(imgDirPath);
            if (!imgDirFile.exists()) {
//                throw new OpException("未定义错误");
                imgDirFile.mkdirs();
            }
            pdDocument = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            PdfReader pdfReader = new PdfReader(filePath);
            pages = pdfReader.getNumberOfPages();
            StringBuffer imgFilePath = null;
            for (int i = 0; i < pages; i++) {
                String imgFilePathPrefix = imgDirPath + "/";
                imgFilePath = new StringBuffer();
                imgFilePath.append(i+1);
                imgFilePath.append(".png");
                File dstFile = new File(imgFilePathPrefix+imgFilePath.toString());
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, dpi);
                ImageIO.write(image, "png", dstFile);
            }
        } catch (IOException e) {
            throw new OpException("文件不存在");
        }
        return pages;
    }

    public int generateImage(String filePath, String relativePath) throws OpException {
        return generateImage(filePath,relativePath,72);
    }
}