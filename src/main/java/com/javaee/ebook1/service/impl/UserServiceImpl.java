package com.javaee.ebook1.service.impl;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.dao.SysUserMapper;
import com.javaee.ebook1.mybatis.daoExt.RoleMapperExt;
import com.javaee.ebook1.mybatis.dto.RoleDto;
import com.javaee.ebook1.mybatis.entity.Role;
import com.javaee.ebook1.mybatis.entity.SysUser;
import com.javaee.ebook1.mybatis.entity.SysUserExample;
import com.javaee.ebook1.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/18
 **/
@Controller
public class UserServiceImpl implements UserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RoleMapperExt roleMapperExt;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMailboxEqualTo(email);
        List<SysUser> entityList = sysUserMapper.selectByExample(example);
        SysUser entity = entityList.stream().findFirst().orElse(null);
        if(entity == null){
            throw new UsernameNotFoundException("登录失败");
        }

        return new User(entity.getMailbox(),entity.getPassword(),userGetRole(entity));
    }

    private Collection<? extends GrantedAuthority> userGetRole(SysUser user){
        List<RoleDto> roleDtos = roleMapperExt.getRoleByUid(user.getUid());
        return roleDtos.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
