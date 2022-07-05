package com.bosonit.bs4.infrastructure;

import com.bosonit.bs4.domain.ProfileImpl1;
import com.bosonit.bs4.domain.ProfileImpl2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping
public class MyController {

    ProfileImpl1 profileImpl1 = new ProfileImpl1();
    ProfileImpl2 profileImpl2 = new ProfileImpl2();

    @PostConstruct
    public void showProfiles() {
        System.out.println("- I am " + profileImpl1.profile + " from MyController.");
        System.out.println("- I am " + profileImpl2.profile + " from MyController.");
    }

    @GetMapping("/profile1")
    public ProfileImpl1 getString1() {
        profileImpl1.myFunction();
        return profileImpl1;
    }

    @GetMapping("/profile2")
    public ProfileImpl2 getString2() {
        profileImpl2.myFunction();
        return profileImpl2;
    }
}
