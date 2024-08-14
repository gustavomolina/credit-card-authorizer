package com.example.authorizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.authorizer.model")
public class AuthorizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizerApplication.class, args);
    }
}
