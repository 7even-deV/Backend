package com.bosonit.jva2.infrastructure;

import com.bosonit.jva2.ApplicationJVA2;
import com.bosonit.jva2.application.IPersonService;
import com.bosonit.jva2.domain.City;
import com.bosonit.jva2.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetController {

    @Autowired
    IPersonService iPersonService;

    @GetMapping("/person/get")
    public Person getPerson() {
        return new Person(
                iPersonService.getPerson().name(),
                iPersonService.getPerson().age(),
                iPersonService.getPerson().city());
    }

    @GetMapping("/city/get")
    public List<City> getCity() {
        return (List<City>) ApplicationJVA2.configurableApplicationContext.getBean("cityList");
    }
}
