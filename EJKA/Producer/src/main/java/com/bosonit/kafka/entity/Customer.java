package com.bosonit.kafka.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String message;
}
