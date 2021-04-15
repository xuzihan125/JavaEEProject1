package com.javaee.ebook1.mybatis.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/4/1
 **/
@Data
public class UserVO {
    private int id;

    private String nickname;

    @NotEmpty
    @Email(message = "邮箱格式错误")
    private String emailAddress;

    @NotEmpty(message = "密码不能为空")
    @Length(max = 16, min = 6, message = "长度在6位~16位之间")
    private String password;

}
