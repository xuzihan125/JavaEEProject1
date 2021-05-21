package com.javaee.ebook1.common.Enum;

public enum RoleEnum {
    NORMAL_READER(0,"USER","normal reader"),
    ADMIN(1,"ADMIN","administrator");

    private int rid;
    private String role;
    private String desc;

    RoleEnum(int rid, String role, String desc) {
        this.rid = rid;
        this.role = role;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getRole() {
        return role;
    }

    public int getRid() {
        return rid;
    }
}
