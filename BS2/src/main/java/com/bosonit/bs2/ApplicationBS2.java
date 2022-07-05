package com.bosonit.bs2;

import com.bosonit.bs2.domain.City;
import com.bosonit.bs2.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApplicationBS2 {
    public static ConfigurableApplicationContext configurableApplicationContext;

    public static void main(String[] args) {
        configurableApplicationContext = SpringApplication.run(ApplicationBS2.class, args);
    }

    @Bean
    public List<City> cityList() {
        return new ArrayList<City>();
    }

    @Bean("bean1")
    public Person person1() {
        return Person.builder().name("bean1").age(10).city("Sevilla").build();
    }

    @Bean("bean2")
    public Person person2() {
        return Person.builder().name("bean2").age(20).city("Madrid").build();
    }

    @Bean("bean3")
    public Person person3() {
        return Person.builder().name("bean3").age(30).city("Barcelona").build();
    }
}
