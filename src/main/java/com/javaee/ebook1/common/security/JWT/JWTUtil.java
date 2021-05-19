package com.javaee.ebook1.common.security.JWT;

import com.javaee.ebook1.common.Enum.ResultCode;
import com.javaee.ebook1.common.Enum.SessionAttribute;
import com.javaee.ebook1.common.exception.OpException;
import com.javaee.ebook1.mybatis.dao.RoleMapper;
import com.javaee.ebook1.mybatis.dao.UserRoleMapper;
import com.javaee.ebook1.mybatis.dto.AuthDTO;
import com.javaee.ebook1.mybatis.dto.RoleDto;
import com.javaee.ebook1.mybatis.dto.UserDTO;
import com.javaee.ebook1.mybatis.entity.UserRole;
import com.javaee.ebook1.service.UserService;
import com.javaee.ebook1.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author xuzihan
 * @version 1.0
 * @description: TODO
 * @data 2021/5/8
 **/
@Component
public class JWTUtil {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private int expiration;

    /**
     * @description: 获得token内容
     * @author xuzih
     * @date 2021/5/18 2:38
     * @version 1.0
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * @description: 获得用户名
     * @author xuzih
     * @date 2021/5/18 2:39
     * @version 1.0
     */
    public String getUserNameFromToken(String token){
        String userName;
        try{
            userName = (String)getClaimsFromToken(token).get(SessionAttribute.USERNAME.getCode());
        }
        catch (Exception e){
            userName = null;
        }
        return userName;
    }

    /**
     * @description: 设置过期时间
     * @author xuzih
     * @date 2021/5/18 2:39
     * @version 1.0
     */
    public Date getExpiration(){
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * @description: 生成token内容
     * @author xuzih
     * @date 2021/5/18 2:39
     * @version 1.0
     */
    public Map<String,Object> getClaim(UserDTO userDTO){
        Map<String,Object> claims = new HashMap<>();
        claims.put(SessionAttribute.USERNAME.getCode(),userDTO.getUsername());
        claims.put(SessionAttribute.PASSWORD.getCode(),userDTO.getPassword());
        claims.put(SessionAttribute.ROLE.getCode(),userDTO.getRoles());
        return claims;
    }


    /**
     * @description: 创建JWT
     * @author xuzih
     * @date 2021/5/18 2:39
     * @version 1.0
     */
    public String generateJWT(UserDTO userDTO){
        return Jwts.builder()
                .setClaims(getClaim(userDTO))
                .setIssuedAt(new Date())
                .setExpiration(getExpiration())
                .signWith(SignatureAlgorithm.ES256,secret)
                .compact();
    }

    public String generateJwtToken(Authentication authentication) {

        AuthDTO userPrincipal = (AuthDTO) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration*1000))
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }


    /**
     * @description: 验证jwt
     * @author xuzih
     * @date 2021/5/18 2:40
     * @version 1.0
     */
    public boolean validJWT(String token) throws OpException {
        Claims claims = getClaimsFromToken(token);
        if(claims == null){
            throw new OpException(ResultCode.TOKENERROR.getDesc(),ResultCode.TOKENERROR.getCode());
        }
        return true;
    }

    /**
     * @description: 生成user(token验证)
     * @author xuzih
     * @date 2021/5/18 3:27
     * @version 1.0
     */
    public UserDetails getUserDetails(String token){
        Claims claims = getClaimsFromToken(token);
        if(claims==null){
            throw new OpException(ResultCode.TOKENERROR.getDesc(),ResultCode.TOKENERROR.getCode());
        }
        String username = (String)claims.get(SessionAttribute.USERNAME.getCode());
        String password = (String)claims.get(SessionAttribute.PASSWORD.getCode());
        Object rolesObject = claims.get(SessionAttribute.ROLE.getCode());
        List<GrantedAuthority> roles = new ArrayList<>();
        if(rolesObject instanceof ArrayList<?>){
            for(Object obj:(List<?>)rolesObject){
                roles.add(new SimpleGrantedAuthority(((RoleDto)obj).getRole()));
            }
        }
        return new User(username,password,roles);


    }
}
