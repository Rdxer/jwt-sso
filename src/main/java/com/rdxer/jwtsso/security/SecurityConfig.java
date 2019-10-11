package com.rdxer.jwtsso.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;
    @Resource
    PasswordEncoder bCryptPasswordEncoder;


    @Bean
    BCryptPasswordEncoder cryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
                .csrf()
            .disable();
        http.rememberMe().disable();
        // 禁用缓存
        http.headers().cacheControl().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                    // 任何  OPTIONS 放行
                .antMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                .antMatchers("/test3").permitAll()
                .antMatchers("/user/*").hasRole("USER")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/super_admin/*").hasRole("SUPER_ADMIN")
                    // 注册放行
                .anyRequest().authenticated()
                    // 任何 请求都需要进行 身份验证
                ;

        http.addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),userDetailsService));


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
        ;
    }
}
