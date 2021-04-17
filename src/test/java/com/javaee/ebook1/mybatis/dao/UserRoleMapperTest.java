package com.javaee.ebook1.mybatis.dao;

import com.javaee.ebook1.mybatis.entity.UserRole;
import com.javaee.ebook1.mybatis.entity.UserRoleExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRoleMapperTest {

    @Resource
    UserRoleMapper userrolemapper;

    @Test
    public void UserRoleMapperTest1(){
        System.out.println("Test User Role insert");
        UserRole userrole = new UserRole();
        userrole.setUid(10);
        userrole.setRid(1);
        int lines = userrolemapper.insert(userrole);
    }

    @Test
    public void UserRoleMapperTest2(){
        System.out.println("Test User Role select");
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(10);
        List<UserRole> userroles = userrolemapper.selectByExample(example);
        UserRole userRole = userroles.get(0);
        System.out.println("The select user role : " + userRole.toString());
    }

    @Test
    public void UserRoleMapperTest3(){
        System.out.println("Test User Role update");
        System.out.println("Test User Role select");
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(10);
        List<UserRole> userroles = userrolemapper.selectByExample(example);
        UserRole userrole = userroles.get(0);
        System.out.println("Before Update user role : " + userrole.toString());
        userrole.setRid(0);
        userrolemapper.updateByExample(userrole, example);

        UserRoleExample exampleAfter = new UserRoleExample();
        example.createCriteria().andUidEqualTo(10);
        List<UserRole> userrolesAfter = userrolemapper.selectByExample(example);
        UserRole userroleAfter = userrolesAfter.get(0);
        System.out.println("After Update user role : " + userroleAfter.toString());
    }

    @Test
    public void UserRoleMapperTest4(){
        System.out.println("Test User Role delete");
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(10);

        int lines = userrolemapper.deleteByExample(example);
        System.out.println("After delete, the number of changed line : " + lines);
    }
}