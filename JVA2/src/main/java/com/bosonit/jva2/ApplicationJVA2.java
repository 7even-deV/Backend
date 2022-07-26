package com.bosonit.jva2;

import com.bosonit.jva2.domain.City;
import com.bosonit.jva2.domain.Person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApplicationJVA2 {
    public static ConfigurableApplicationContext configurableApplicationContext;

    public static void main(String[] args) {
        configurableApplicationContext = SpringApplication.run(ApplicationJVA2.class, args);
    }

    @Bean
    public List<City> cityList() {
        return new ArrayList<City>();
    }

    @Bean("bean1")
    public Person person1() {
        return new Person("Bean1", 10, "Sevilla");
    }

    @Bean("bean2")
    public Person person2() {
        return new Person("Bean2", 20, "Madrid");
    }

    @Bean("bean3")
    public Person person3() {
        return new Person("Bean3", 30, "Barcelona");
    }
}
