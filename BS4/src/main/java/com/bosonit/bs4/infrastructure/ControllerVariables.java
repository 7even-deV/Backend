package com.bosonit.bs4.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@PropertySource("myConfiguration.properties")
public class ControllerVariables {

    @Value("${value1}") String value1;
    @Value("${value2}") String value2;

    @GetMapping("/values")
    public String getVariables() {
        return value1 + "\n" + value2;
    }
}
