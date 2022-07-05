package com.bosonit.bs2.infrastructure;

import com.bosonit.bs2.ApplicationBS2;
import com.bosonit.bs2.application.IPersonService;
import com.bosonit.bs2.domain.City;
import com.bosonit.bs2.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controller2")
public class Controller2 {

    @Autowired
    IPersonService iPersonService;

    @GetMapping("/getPerson")
    public Person getPerson() {
        Person newPerson = iPersonService.getPerson();
        newPerson.setAge(newPerson.getAge() * 2);
        return iPersonService.getPerson();
    }

    @GetMapping("/getCity")
    public List<City> getCity() {
        return (List<City>) ApplicationBS2.configurableApplicationContext.getBean("cityList");
    }
}
