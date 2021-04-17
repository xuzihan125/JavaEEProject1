package com.javaee.ebook1.mybatis.dao;

import com.javaee.ebook1.mybatis.entity.Books;
import com.javaee.ebook1.mybatis.entity.BooksExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
//@RunWith(SpringRunner.class)
class BooksMapperTest {

    @Resource
    private BooksMapper booksmapper;

    @Test
    public void selectBookTest(){
        System.out.println("Test books select");

        Books book = booksmapper.selectByPrimaryKey(12);
        System.out.println(book.toString());
    }

    @Test
    public void insertBookTest(){
        System.out.println("Test books insert");

        Books book = new Books();
        book.setAuthor("aaa");
        book.setBookName("wwwww");
        book.setDescr("ccc");
        book.setBid(101);

        int lines = booksmapper.insert(book);
        assertEquals(lines, 1);
    }

    @Test
    public void updateBookTest(){
        System.out.println("Test books update");

        Books book = booksmapper.selectByPrimaryKey(12);
        System.out.println(book.toString());
        if(book.getAuthor().equals("abcde")){
            book.setAuthor("jklmn");
        }else{
            book.setAuthor("abcde");
        }

        booksmapper.updateByPrimaryKey(book);
        Books book_new = booksmapper.selectByPrimaryKey(12);
        System.out.println(book.toString());
    }

    @Test
    public void deleteBookTest(){
        System.out.println("Test books delete");
        BooksExample example = new BooksExample();
        example.createCriteria().andAuthorEqualTo("aaa");
        int lines = booksmapper.deleteByExample(example);
        System.out.println("The number of delete lines : " + lines);
    }

}