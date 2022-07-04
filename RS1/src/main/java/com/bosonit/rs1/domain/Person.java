package com.bosonit.rs1.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {
    private Integer id;
    private String name;
    private int age;
    private String city;
}
