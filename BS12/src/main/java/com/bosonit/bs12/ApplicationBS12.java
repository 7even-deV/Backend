package com.bosonit.bs12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.bosonit.bs12.feign")
public class ApplicationBS12 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBS12.class, args);
    }
}
