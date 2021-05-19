package com.javaee.ebook1.controller;

import com.javaee.ebook1.common.security.JWT.JWTUtil;
import com.javaee.ebook1.mybatis.dto.AuthDTO;
import com.javaee.ebook1.mybatis.dto.LoginDTO;
import com.javaee.ebook1.mybatis.vo.JWTVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 22021/5/18
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    JWTUtil jwtUtils;

    @PostMapping("/signin")
    @ApiOperation(value = "token授权")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AuthDTO userDetails = (AuthDTO) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JWTVO(jwt));
    }
}
