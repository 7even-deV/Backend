package com.bosonit.bs4.domain;

import com.bosonit.bs4.application.IProfiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "profile1")
public class ProfileImpl1 implements IProfiles {

    @Value("${profile1}")
    public String profile = "profile1";

    @Override
    public void myFunction() {
        System.out.println("- I am " + profile + " from ProfileImpl1.");
    }
}
