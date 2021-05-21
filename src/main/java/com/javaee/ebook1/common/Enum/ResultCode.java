package com.javaee.ebook1.common.Enum;

public enum ResultCode {
    SUCCESS("000000","返回成功"),
    TOKENERROR("400001","token错误"),
    EMAILNOTEXIST("400002","账号不存在"),
    INVALID_INPUT("100001","输入不满足约束条件"),
    INVALID_ATTRIBUTE("100002","参数错误"),
    NOT_EXIST_BOOK("200001","满足条件的书不存在"),
    FILE_OPERATION_FAIL("500001","文件操作失败"),
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
