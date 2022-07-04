package com.bosonit.bs0.infrastructure;

import com.bosonit.bs0.domain.Person;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @GetMapping("/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name;
    }

    @PostMapping("/useradd")
    public Person userAdd(@RequestBody @NotNull Person person) {
        person.setAge(person.getAge() + 1);
        return person;
    }
}
