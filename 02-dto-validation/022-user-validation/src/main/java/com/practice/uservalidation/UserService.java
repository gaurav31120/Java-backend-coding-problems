package com.practice.uservalidation;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest) {

        int nextId = userRepository.getNextId();
        User user = new User(nextId, userRequest.getName());

        userRepository.addUser(user);

        return user;

    }
}
