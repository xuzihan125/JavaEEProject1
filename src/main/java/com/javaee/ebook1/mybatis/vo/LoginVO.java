package com.javaee.ebook1.mybatis.vo;

import lombok.Data;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/20
 **/
@Data
public class LoginVO {
    private Boolean status;
    private String nickname;
    private String Token;
    private String emailAddress;
}
