package com.practice.constructorinjection;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String userService() {
        return "User service is working";
    }
}
