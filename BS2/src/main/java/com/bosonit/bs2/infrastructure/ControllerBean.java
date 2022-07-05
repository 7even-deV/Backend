package com.bosonit.bs2.infrastructure;


import com.bosonit.bs2.ApplicationBS2;
import com.bosonit.bs2.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controllerBean")
public class ControllerBean {

    @GetMapping("/{bean}")
    public Person getPersonBean(@PathVariable("bean") String name) {
        return (Person) ApplicationBS2.configurableApplicationContext.getBean(name);
    }
}
