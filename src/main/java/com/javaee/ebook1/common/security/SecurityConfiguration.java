package com.javaee.ebook1.common.security;

import com.javaee.ebook1.service.LoginService;
import com.javaee.ebook1.service.impl.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;


/**
 * @author xuzihan
 * @version 1.0
 * @description: 权限管理
 * @data 2021/3/30
 **/
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }
}
//    @Resource
//    private LoginService loginService;
//
//    @Autowired
//    private MyFailureLoginhandler myFailureLoginhandler;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //SpringSecurity 提供的一种编码器，我们也可以自己实现PasswordEncoder
//        //目前springboot推荐这种加密方式
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        //图片等传输
//        http.authorizeRequests()
//                .antMatchers(
//                "/registration**",
//                "/js/**",
//                "/css/**",
//                "/img/**",
//                "/webjars/**").permitAll()
//                .antMatchers("/log").hasRole("ADMIN")
//                .anyRequest().authenticated();
//        //登录界面
//        http.formLogin().loginPage("/login").permitAll()
//                .loginProcessingUrl("/login/attempt")
//                .successForwardUrl("/booksList")
//                .failureForwardUrl("/login")
//                .failureHandler(myFailureLoginhandler);
//
//        //登出界面
//        http.logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                //登出成功后页面
//                .logoutSuccessUrl("/login")
//                .permitAll();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(loginService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//}
