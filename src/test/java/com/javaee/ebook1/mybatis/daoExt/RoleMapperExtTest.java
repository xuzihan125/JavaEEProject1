package com.javaee.ebook1.mybatis.daoExt;

import com.javaee.ebook1.mybatis.dto.RoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleMapperExtTest {

    @Resource
    RoleMapperExt rolemapperext;

    @Test
    void getRoleByUid() {
        List<RoleDto> roledtos = rolemapperext.getRoleByUid(2);
        for(RoleDto roletod : roledtos){
            System.out.println(roletod.toString());
        }
    }
}