package com.bosonit.rs1.infrastructure;

import com.bosonit.rs1.application.interfaces.IUserService;
import com.bosonit.rs1.domain.User;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @PostMapping
    public User setUser(@RequestBody User user) {
        iUserService.setUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable int id) {
        return "User ID: " + id;
    }

    @PutMapping
    public User putUser(@RequestParam int id, @RequestParam String name) {
        return User.builder()
                .id(id)
                .name(name)
                .build();
    }
}
