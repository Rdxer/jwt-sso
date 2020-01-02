package com.rdxer.jwtsso.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
    }

    /**
     * 重写 父类 此方法，解析请求体的数据
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username;
        String password;
        if ("application/json".equals(request.getContentType())){
            LoginPara loginPara = objectMapper.readValue(request.getInputStream(), LoginPara.class);
            username = loginPara.getUsername();
            password = loginPara.getPassword();
        }else{
            username = obtainUsername(request);
            password = obtainPassword(request);
        }


        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse resp,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        long time = JWTConfiguration.sessionTime;


        User user = (User) auth.getPrincipal();

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(SignatureAlgorithm.HS512, JWTConfiguration.secret)
                .compact();

        resp.addHeader(JWTConfiguration.header_refresh_key, JWTConfiguration.jwt_prefix + " " + token);
    }
}

@Data
class LoginPara{
    private String username;
    private String password;
}