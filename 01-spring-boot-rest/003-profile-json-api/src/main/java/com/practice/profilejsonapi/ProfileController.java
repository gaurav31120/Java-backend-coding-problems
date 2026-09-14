package com.practice.profilejsonapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @GetMapping("/profile")
    public Profile profile() {

        Profile profile = new Profile("Gaurav", 25, "Patna");
        return profile;
    }
}
