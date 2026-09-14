package com.practice.responseentity;

import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    public User findById(int id) {
        return userRepository.findById(id);

    }



}

