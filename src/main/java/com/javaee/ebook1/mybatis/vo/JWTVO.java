package com.javaee.ebook1.mybatis.vo;

import lombok.Data;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/18
 **/
@Data
public class JWTVO {
    private String token;
    private String type = "Bearer";

    public JWTVO(String token){
        this.token = token;
    }
}
