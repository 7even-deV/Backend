package com.bosonit.bs5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class ApplicationBS5 {

    public static ConfigurableApplicationContext configurableApplicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBS5.class, args);
    }
}
