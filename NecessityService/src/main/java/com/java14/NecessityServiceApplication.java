package com.java14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NecessityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NecessityServiceApplication.class, args);
    }
}