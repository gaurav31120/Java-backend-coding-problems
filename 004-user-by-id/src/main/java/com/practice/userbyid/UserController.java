package com.practice.userbyid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/users/{id}")
    public User user(@PathVariable("id") int id) {
        User user = new User(id, "Gaurav");
        return user;
    }
}