package com.rdxer.jwtsso.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdxer.lib.exception.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JWTConfiguration.header_auth_key);

        if (header == null || !header.startsWith(JWTConfiguration.jwt_prefix)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    /// 获取 -> 登录信息
    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWTConfiguration.header_auth_key);

        if (token != null) {
            // parse the token.

            var jwt = Jwts.parser()
                    .setSigningKey(JWTConfiguration.secret)
                    .parseClaimsJws(token.replace(JWTConfiguration.jwt_prefix, "").trim());
            Claims body = jwt.getBody();
            String username =  body.getSubject();

            if (username != null) {

                User userDetails = (User) userDetailsService.loadUserByUsername(username);

                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
