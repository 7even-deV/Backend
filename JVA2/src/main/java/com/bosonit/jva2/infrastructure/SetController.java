package com.bosonit.jva2.infrastructure;

import com.bosonit.jva2.ApplicationJVA2;
import com.bosonit.jva2.application.IPersonService;
import com.bosonit.jva2.domain.City;
import com.bosonit.jva2.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SetController {

    @Autowired
    IPersonService iPersonService;

    @GetMapping("/person/set")
    public Person setPerson(@RequestHeader Map<String, String> header) {
        Person person = new Person(
                header.get("name"),
                Integer.parseInt(header.get("age")),
                header.get("city"));
        iPersonService.addPerson(person);
        return person;
    }

    @PostMapping("/city/set")
    public City setCity(@RequestBody City city) {
        List<City> cityList = (List<City>) ApplicationJVA2.configurableApplicationContext
                .getBean("cityList");
        cityList.add(city);
        return city;
    }
}
