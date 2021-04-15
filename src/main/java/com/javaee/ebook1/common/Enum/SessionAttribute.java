package com.javaee.ebook1.common.Enum;

public enum SessionAttribute {
    ROLE("role","user's role");
    private String code;
    private String desc;

    SessionAttribute(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
