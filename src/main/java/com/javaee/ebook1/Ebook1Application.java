package com.javaee.ebook1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.javaee.ebook1.mybatis.dao","com.javaee.ebook1.mybatis.daoExt"})
@SpringBootApplication
public class Ebook1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ebook1Application.class, args);
    }

}
