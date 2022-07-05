package com.bosonit.bs5.domain;

import com.bosonit.bs5.application.IProfiles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "profile2")
public class ProfileImpl2 implements IProfiles {

    @Value("${profile2}")
    public String profile = "profile2";

    @Override
    public void myFunction() {
        System.out.println("- I am " + profile + " from ProfileImpl2.");
    }
}
