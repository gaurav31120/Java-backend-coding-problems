package com.practice.usernotfound;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);

    }
}
