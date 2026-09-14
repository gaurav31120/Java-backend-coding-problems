package com.practice.putuser;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(int id, User updatedUser) {
        return userRepository.updateUser(id, updatedUser);

    }
}

