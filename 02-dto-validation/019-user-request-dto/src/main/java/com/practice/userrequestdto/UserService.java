package com.practice.userrequestdto;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(UserRequest userRequest) {
        User user = new User(
                userRepository.getNextId(),
                userRequest.getName()
        );

        userRepository.addUser(user);

        return user;
    }
}