package com.javaee.ebook1.service.impl;


//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;


/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/3/31
 **/
//@Service
public class AuthenticationProviderImpl {//implements AuthenticationProvider {
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq(ColumnName.USER_MAILBOX.getName(),name);
//        User user = userMapper.selectOne(wrapper);
//        if(user == null){
//            throw new BadCredentialsException("登陆失败");
//        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        if(new BCryptPasswordEncoder().matches(password,user.getPassword())){
//            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+"admin"));
//        }
//        return null;
//    }
}
