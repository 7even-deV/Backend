package com.bosonit.bs5.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ControllerTest {

    @Value("${VAR1}")
    String firstValue;
    @Value("${My.VAR2}")
    int secondValue;
    @Value("${VAR3: without value}")
    String threeValue;

    @GetMapping("/values")
    public String getValues() {
        return "VAR1: " + firstValue + "\n" + "My.VAR2: " + secondValue;
    }

    @GetMapping("/var3")
    public String getValue3() {
        return "VAR3:" + threeValue;
    }
}
