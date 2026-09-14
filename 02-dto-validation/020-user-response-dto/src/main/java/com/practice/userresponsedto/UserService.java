package com.practice.userresponsedto;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUser(int id) {
        User user = userRepository.findById(id);

        if (user == null) {
            return null;
        }

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName()
        );

        return userResponse;
    }
}