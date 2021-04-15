package com.javaee.ebook1.common.Enum;

public enum ColumnName {
    BOOKS_BNAME("book_name"),
    BOOKS_AUTHOR("writter"),
    USER_MAILBOX("mailbox"),
    USER_PASSWORD("password");


    private String name;

    private ColumnName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
