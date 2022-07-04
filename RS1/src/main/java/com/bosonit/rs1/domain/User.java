package com.bosonit.rs1.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private Integer id;
    private String name;
}
