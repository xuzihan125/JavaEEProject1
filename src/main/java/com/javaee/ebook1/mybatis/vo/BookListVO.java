package com.javaee.ebook1.mybatis.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/1
 **/
@Data
public class BookListVO {
    private String name;

    private String author;

    @NotNull
    @Min(value = 1,message = "页码输入错误")
    private int pageNo;

    @NotNull
    @Max(value = 50,message = "页面显示范围过大")
    @Min(value = 10,message = "页面显示范围过小")
    private int pageSize;

    public BookListVO(String name, String author, @NotNull @Length(min = 1, message = "未输入页码") int pageNo, @NotNull @Length(min = 10, max = 50, message = "页面显示范围错误") int pageSize) {
        this.name = name;
        this.author = author;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
