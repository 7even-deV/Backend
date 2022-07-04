package com.bosonit.bs2.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {
    private String name;
    private int age;
    private String city;
}
