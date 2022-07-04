package com.bosonit.bs3.infrastructure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MyController implements CommandLineRunner {

    @Bean
    public CommandLineRunner methodController() {
        return p -> {
            System.out.println(
                    "5 - This is executed after the execute method of the class that implements CommandLineRunner: MyController.");
        };
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("2 - Hello from the Secondary Class: MyController.");
        System.out.println(Arrays.toString(args));
    }
}
