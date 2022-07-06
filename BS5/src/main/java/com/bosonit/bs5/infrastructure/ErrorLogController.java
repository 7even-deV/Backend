package com.bosonit.bs5.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ErrorLogController {

    @GetMapping("/errors")
    public String index() {

        log.error(String.valueOf(new ExceptionInInitializerError()));
        return "- This is a error from ErrorLogController.";
    }
}
