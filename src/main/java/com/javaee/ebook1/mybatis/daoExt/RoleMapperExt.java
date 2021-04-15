package com.javaee.ebook1.mybatis.daoExt;

import com.javaee.ebook1.mybatis.dto.RoleDto;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapperExt {
    @Select("select * from user_role ur, role r where ur.uid = #{uid} and r.rid = ur.rid")
    @Results(id = "RoleMapperExt",value = {
        @Result(column="uid",property = "uid"),
        @Result(column="rid",property = "rid"),
        @Result(column="role",property = "role"),
        @Result(column="descri",property = "descri"),
    })
    List<RoleDto> getRoleByUid(int uid);

}
