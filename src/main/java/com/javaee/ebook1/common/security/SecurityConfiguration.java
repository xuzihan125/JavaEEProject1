package com.javaee.ebook1.common.security;

import com.javaee.ebook1.common.Enum.RoleEnum;
import com.javaee.ebook1.common.Enum.SessionAttribute;
import com.javaee.ebook1.common.security.JWT.AuthTokenFilter;
import com.javaee.ebook1.service.LoginService;
import com.javaee.ebook1.service.UserService;
import com.javaee.ebook1.service.impl.AuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;


/**
 * @author xuzihan
 * @version 1.0
 * @description: 权限管理
 * @data 2021/3/30
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        //SpringSecurity 提供的一种编码器，我们也可以自己实现PasswordEncoder
        //目前springboot推荐这种加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Configuration
    @Order(1)
    public static class apiSecurityConfig extends WebSecurityConfigurerAdapter{
        @Resource
        private AuthTokenFilter jwtauthFilter;

        @Resource
        private UserService userService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class);

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }


    @Configuration
    @Order(2)
    public static class webSecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Resource
        private UserService userService;

        @Bean
        public DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(userService);
            auth.setPasswordEncoder(passwordEncoder());
            return auth;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http
                    .authorizeRequests()
                    .antMatchers(
                            "/registration**",
                            "/js/**",
                            "/css/**",
                            "/img/**",
                            "/webjars/**",
                            "/swagger-ui/**"
                    )
                    .permitAll()
                    .antMatchers("/user/**").hasRole(RoleEnum.NORMAL_READER.getRole())
                    .antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.getRole())
                    .antMatchers("/regist").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login")
                    .permitAll()
                    .loginProcessingUrl("/switch")
                    .and()
                    .logout().invalidateHttpSession(true).clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                    .and()
                    .csrf().disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }
    }
}
