package com.practice.registrationvalidation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/register")
    public RegistrationRequest registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return registrationRequest;

    }
}
