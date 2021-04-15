package com.javaee.ebook1.common.Enum;

public enum ResultCode {
    SUCCESS("000000","返回成功"),
    INVALID_INPUT("100001","输入不满足约束条件"),
    NOT_EXIST_BOOK("200001","满足条件的书不存在"),
    INVALID_AUTHOR("300001","权限错误"),
    ERROR_LIST_COPY("800001","链表复制错误");


    private String code;
    private String desc;

    private ResultCode(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
