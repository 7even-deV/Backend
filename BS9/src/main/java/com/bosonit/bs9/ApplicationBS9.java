package com.bosonit.bs9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Inform to Spring that we are going to use Feign
public class ApplicationBS9 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBS9.class, args);
    }
}
