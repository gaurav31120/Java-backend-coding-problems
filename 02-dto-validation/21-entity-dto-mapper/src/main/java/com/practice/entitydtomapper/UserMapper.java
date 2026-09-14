package com.practice.entitydtomapper;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {

        UserResponse userResponse = new UserResponse(user.getId(), user.getName());

        return userResponse;

    }
}
