package com.rdxer.jwtsso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.rdxer.jwtsso","com.rdxer.lib"})
@EnableJpaAuditing
@EnableSwagger2
public class JwtSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSsoApplication.class, args);

    }
    @Bean
    BCryptPasswordEncoder cryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
