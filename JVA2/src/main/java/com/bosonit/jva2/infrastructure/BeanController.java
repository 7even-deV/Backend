package com.bosonit.jva2.infrastructure;

import com.bosonit.jva2.ApplicationJVA2;
import com.bosonit.jva2.domain.Person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

    @GetMapping("/person/bean/{bean}")
    public Person getBean(@PathVariable("bean") String bean) {
        return (Person) ApplicationJVA2.configurableApplicationContext.getBean(bean);
    }
}
