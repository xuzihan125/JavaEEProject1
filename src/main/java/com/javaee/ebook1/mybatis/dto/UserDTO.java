package com.javaee.ebook1.mybatis.dto;

import lombok.Data;

import java.util.List;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/8
 **/
@Data
public class UserDTO {
    String username;
    String password;
    List<RoleDto> roles;
}
