package com.practice.validationerrorapi;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    @NotBlank
    String name;
    @NotBlank
    @Email
    String email;
    @NotBlank
    @Size(min = 8)
    String password;

    public RegistrationRequest() {

    }

    public RegistrationRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

}