package com.practice.controllerservice;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserProfile() {
        return "User profile from service";
    }

}
