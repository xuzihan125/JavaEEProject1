package com.javaee.ebook1.common.generator;

import com.javaee.ebook1.common.util.FileUtil;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/3
 **/
public class MybatisGenerator {

    public void generator(String path) throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args){
        FileUtil fileUtil = new FileUtil();
        String config ="";

        config = fileUtil.getFileSourceFromResource("config/mapperConfig.xml");
        MybatisGenerator mybatisGenerator = new MybatisGenerator();
        try{
            mybatisGenerator.generator(config);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
