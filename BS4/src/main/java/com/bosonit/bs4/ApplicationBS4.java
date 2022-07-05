package com.bosonit.bs4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class ApplicationBS4 {

	public static ConfigurableApplicationContext configurableApplicationContext;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBS4.class, args);
	}
}
