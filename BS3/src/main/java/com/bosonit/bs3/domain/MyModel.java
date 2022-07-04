package com.bosonit.bs3.domain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyModel {

    @Bean
    CommandLineRunner methodModelClass() {
        return p -> {
            System.out.println("4 - Hello from the Third Class: MyModel.");
        };
    }
}
