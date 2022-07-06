package com.bosonit.bs5.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggingController {

    @RequestMapping()
    public String index() {

        log.trace("- I am log TRACE message from LoggingController.");
        log.debug("- I am log DEBUG message from LoggingController.");
        log.info("- I am log INFO message from LoggingController.");
        log.warn("- I am log WARN message from LoggingController.");
        log.error("- I am log ERROR message from LoggingController.");

        return "- Howdy! Check out the Logs to see the output... from LoggingController.";
    }
}
