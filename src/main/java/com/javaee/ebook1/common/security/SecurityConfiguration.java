package com.javaee.ebook1.common.security;

import com.javaee.ebook1.common.Enum.RoleEnum;
import com.javaee.ebook1.common.security.JWT.AuthEntryPointJwt;
import com.javaee.ebook1.common.security.JWT.AuthTokenFilter;
import com.javaee.ebook1.common.security.JWT.JWTAccessDenied;
import com.javaee.ebook1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Configuration
    @EnableWebSecurity
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
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/api/**").hasAnyRole("API")
                    .and()
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
    @EnableWebSecurity
    @Order(2)
    public static class webSecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Resource
        private UserService userService;

        @Resource
        private AuthEntryPointJwt authEntryPointJwt;

        @Resource
        private JWTAccessDenied jwtAccessDenied;

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
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    )
                    .permitAll()
                    .and().authorizeRequests().antMatchers("/regist").permitAll()
                    .and().authorizeRequests().antMatchers("/user/**").hasRole(RoleEnum.NORMAL_READER.getRole())
                    .and().authorizeRequests().antMatchers("/admin/**").hasRole(RoleEnum.ADMIN.getRole())
                    .and().authorizeRequests().anyRequest().authenticated()
                    .and().exceptionHandling().accessDeniedHandler(jwtAccessDenied)
                    .and()
                    .formLogin().loginPage("/login")
                    .permitAll()
                    .and()
                    .logout().invalidateHttpSession(true).clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                    .and().httpBasic().authenticationEntryPoint(authEntryPointJwt)
                    .and()
                    .csrf().disable();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            //swagger2所需要用到的静态资源，允许访问
            web.ignoring().antMatchers("/v2/api-docs",
                    "/swagger-resources/configuration/ui",
                    "/swagger-resources",
                    "/swagger-resources/configuration/security",
                    "/swagger-ui.html");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

    }
}
