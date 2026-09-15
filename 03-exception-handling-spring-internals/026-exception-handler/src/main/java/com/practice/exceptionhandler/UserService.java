package com.practice.exceptionhandler;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        User user = userRepository.findById(id);

        if(user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user;
    }
}
