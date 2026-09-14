package com.practice.deleteuser;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);

    }
}
