package com.practice.deleteuser;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User deleteUser(int id) {
        return userRepository.deleteUser(id);
    }


}
