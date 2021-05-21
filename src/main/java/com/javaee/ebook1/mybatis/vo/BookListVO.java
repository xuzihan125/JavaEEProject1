package com.javaee.ebook1.mybatis.vo;

import com.javaee.ebook1.mybatis.entity.Books;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @description: TODO
 * @data
 **/
@Data
public class BookListVO {
    private List<Books> booksList;
    private boolean hasNext;
    private boolean hasPrev;
}
