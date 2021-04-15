package com.javaee.ebook1.mybatis.vo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/16
 **/
@Data
public class BookVO {
    private Integer bid;

    @NotEmpty(message = "作者不能为空")
    private String author;

    @NotEmpty(message = "书名不能为空")
    private String bookName;

    private String descr;
}
