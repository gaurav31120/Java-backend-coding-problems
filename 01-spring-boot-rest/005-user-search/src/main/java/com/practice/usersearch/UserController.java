package com.practice.usersearch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/users")

    public User search(@RequestParam("name") String name) {
        User user = new User(101, name);
        return user;
            }
}
