package com.bosonit.bs2.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class City {
    private String name;
    private int nPersons;
}
