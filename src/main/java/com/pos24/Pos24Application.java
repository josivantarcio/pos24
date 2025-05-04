package com.pos24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Pos24Application {
    public static void main(String[] args) {
        SpringApplication.run(Pos24Application.class, args);
    }
} 