package com.bosonit.bs5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApplicationBS5 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBS5.class, args);

        log.trace("- I am log TRACE message from ApplicationBS5.");
        log.debug("- I am log DEBUG message from ApplicationBS5.");
        log.info("- I am log INFO message from ApplicationBS5.");
        log.warn("- I am log WARN message from ApplicationBS5.");
        log.error("- I am log ERROR message from ApplicationBS5.");
    }
}
