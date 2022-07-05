package com.bosonit.bs4.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerParams {

    @Value("${url}") String url;
    @Value("${password}") String password;

    @GetMapping("/params")
    public String getVariables() {
        return "url: " + url + "\npassword: " + password;
    }
}
