package com.javaee.ebook1.service.impl;


import com.javaee.ebook1.common.Enum.RoleEnum;
import com.javaee.ebook1.common.Enum.SessionAttribute;
import com.javaee.ebook1.common.security.JWT.JWTUtil;
import com.javaee.ebook1.common.util.ListTool;
import com.javaee.ebook1.mybatis.dao.SysUserMapper;
import com.javaee.ebook1.mybatis.dao.UserRoleMapper;
import com.javaee.ebook1.mybatis.daoExt.RoleMapperExt;
import com.javaee.ebook1.mybatis.dto.RoleDto;
import com.javaee.ebook1.mybatis.dto.UserDTO;
import com.javaee.ebook1.mybatis.entity.SysUser;
import com.javaee.ebook1.mybatis.entity.SysUserExample;
import com.javaee.ebook1.mybatis.entity.UserRole;
import com.javaee.ebook1.mybatis.vo.LoginVO;
import com.javaee.ebook1.mybatis.vo.RegistVO;
import com.javaee.ebook1.mybatis.vo.UserVO;
import com.javaee.ebook1.service.BookListService;
import com.javaee.ebook1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/3/30
 **/

@Service
@Validated
public class LoginServiceImpl implements LoginService  {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BookListService bookListService;

    @Resource
    private RoleMapperExt roleMapperExt;

    @Resource
    private JWTUtil jwtUtil;

//    @Override
//    public boolean checkLogin(String mailbox, String password) {
//        if(mailbox==null || mailbox.equals("") || password==null || mailbox.equals("")){
//            return false;
//        }
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq(ColumnName.USER_MAILBOX.getName(),mailbox);
//        wrapper.eq(ColumnName.USER_PASSWORD.getName(),password);
//        return userMapper.selectCount(wrapper)>0;
//    }

    @Override
    public LoginVO checkLogin(@Valid UserVO userVO){
        //返回变量
        LoginVO loginVO = new LoginVO();
        //获得参数
        String mail = userVO.getEmailAddress();
        String password = userVO.getPassword();
        //校验信息
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMailboxEqualTo(mail);
        List<SysUser> result = sysUserMapper.selectByExample(example);
        SysUser user = result.stream().findFirst().orElse(null);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(user ==null || !passwordEncoder.matches(password,user.getPassword())){
            loginVO.setStatus(false);;
            return loginVO;
        }
        //构造返回值
        loginVO.setStatus(true);
        loginVO.setEmailAddress(mail);
        loginVO.setNickname(user.getNickname());
        //创建token
        UserDTO userDTO  = new UserDTO();
        userDTO.setPassword(password);
        userDTO.setUsername(mail);
        List<RoleDto> roleDtos = roleMapperExt.getRoleByUid(user.getUid());
        userDTO.setRoles(roleDtos);
        loginVO.setToken(jwtUtil.generateJWT(userDTO));
        return loginVO;
    }

    public ModelAndView logout(HttpSession session){
        session.removeAttribute(SessionAttribute.ROLE.getCode());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");
        modelAndView.addObject("logout",true);
        return modelAndView;
    }

    @Override
    public RegistVO regist(@Valid UserVO userVO){
        //创建返回参数
        RegistVO registVO = new RegistVO();
        //获得参数
        String mail = userVO.getEmailAddress();
        SysUserExample example = new SysUserExample();
        example.createCriteria().andMailboxEqualTo(mail);
        List<SysUser> result = sysUserMapper.selectByExample(example);
        SysUser user = result.stream().findFirst().orElse(null);
        if(user !=null){
            registVO.setStatus(false);
            registVO.setContent("邮箱已存在");
        }
        //创建账号
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = userVO.getPassword();
        String nickname = userVO.getNickname();
        SysUser sysUser = new SysUser();
        sysUser.setMailbox(mail);
        sysUser.setNickname(nickname);
        sysUser.setPassword(passwordEncoder.encode(password));
        //授予基础权限
        int uid = sysUserMapper.insert(sysUser);
        UserRole userRole = new UserRole();
        userRole.setUid(sysUser.getUid());
        userRole.setRid(RoleEnum.NORMAL_READER.getRid());
        userRoleMapper.insert(userRole);
        //返回结果
        registVO.setStatus(true);
        registVO.setContent("注册成功");
        return registVO;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        SysUserExample example = new SysUserExample();
//        example.createCriteria().andMailboxEqualTo(email);
//        List<SysUser> result = sysUserMapper.selectByExample(example);
//        SysUser user = result.stream().findFirst().orElse(null);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        List<RoleDto> roles =  roleMapperExt.getRoleByUid(user.getUid());
//        Collection<SimpleGrantedAuthority> authorities = roles.stream().map(role->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//        return new User(email,user.getPassword(),authorities);
//    }


}
