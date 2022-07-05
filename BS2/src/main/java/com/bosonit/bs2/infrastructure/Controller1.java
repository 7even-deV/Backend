package com.bosonit.bs2.infrastructure;

import com.bosonit.bs2.ApplicationBS2;
import com.bosonit.bs2.application.IPersonService;
import com.bosonit.bs2.domain.City;
import com.bosonit.bs2.domain.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controller1")
public class Controller1 {

    @Autowired
    IPersonService iPersonService;

    @GetMapping("/addPerson")
    public Person addPerson(@RequestHeader @NotNull Map<String, String> headers) {
        Person newPerson = Person.builder()
                .name(headers.get("name"))
                .age(Integer.parseInt(headers.get("age")))
                .city(headers.get("city"))
                .build();
        iPersonService.setPerson(newPerson);
        return newPerson;
    }

    @PostMapping("/addCity")
    public City addCity(@RequestBody City city) {
        List<City> beanList = (List<City>) ApplicationBS2.configurableApplicationContext.getBean("cityList");
        beanList.add(city);
        return city;
    }
}
