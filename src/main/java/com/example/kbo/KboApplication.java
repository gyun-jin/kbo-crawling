package com.example.kbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.kbo.data.repository")
@EntityScan(basePackages = "com.example.kbo.data.entity")
@SpringBootApplication(scanBasePackages = {"com.example.kbo.controller", "com.example.kbo.service", "com.example.kbo.data.repository", "com.example.kbo.data.entity"})
public class KboApplication {
    public static void main(String[] args) {
        SpringApplication.run(KboApplication.class, args);
    }
}
