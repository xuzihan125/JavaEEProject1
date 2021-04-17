package com.javaee.ebook1.mybatis.dao;

import com.javaee.ebook1.mybatis.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserMapperTest {

    @Resource
    SysUserMapper sysUserMapper;

    @Test
    public void SysUserMapperTest1(){
        System.out.println("Test sys_user insert");
        SysUser sysuser = new SysUser();
        sysuser.setMailbox("mushroom@abc.com");
        sysuser.setNickname("HappyMushroom");
        sysuser.setPassword("123456");
        sysuser.setUid(123);
        int lines = sysUserMapper.insert(sysuser);
        assertEquals(lines, 1);
    }

    @Test
    public void SysUserMapperTest2(){
        System.out.println("Test sys_user select");
        SysUser sysuser = sysUserMapper.selectByPrimaryKey(4);
        System.out.println("Select Sys User is : " + sysuser.toString());
    }

    @Test
    public void SysUserMapperTest3(){
        System.out.println("Test sys_user select");
        SysUser sysuser = sysUserMapper.selectByPrimaryKey(4);
        System.out.println("Select Sys User is : " + sysuser.toString());
        System.out.println("Before update : " + sysuser.toString());
        sysuser.setNickname("SadMushroom");
        sysUserMapper.updateByPrimaryKey(sysuser);
        System.out.println("After update : " + sysuser.toString());
    }

    @Test
    public void SysUserMapperTest4(){
        System.out.println("Test sys_user delete");
        int lines = sysUserMapper.deleteByPrimaryKey(4);
        assertEquals(lines, 1);
    }
}