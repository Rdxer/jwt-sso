package com.rdxer.jwtsso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan({"com.rdxer.jwtsso","com.rdxer.lib"})
@EnableJpaAuditing
public class JwtSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSsoApplication.class, args);

    }

}
