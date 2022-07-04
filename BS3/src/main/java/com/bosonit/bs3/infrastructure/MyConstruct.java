package com.bosonit.bs3.infrastructure;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MyConstruct {

    @PostConstruct
    public void myMethod() {
        System.out.println("1 - Hello from the Initial Class: MyConstruct.");
    }
}
