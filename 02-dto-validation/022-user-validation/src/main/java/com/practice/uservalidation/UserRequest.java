package com.practice.uservalidation;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    String name;

    public UserRequest() {

    }

    public UserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
