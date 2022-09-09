package com.bosonit.backweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackWebApplication.class, args);
    }
}
