package com.bosonit.bs3.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Bean
    public CommandLineRunner methodComponentClass() {
        return p -> {
            System.out.println("3 - I am the MyComponent.");
        };
    }
}
