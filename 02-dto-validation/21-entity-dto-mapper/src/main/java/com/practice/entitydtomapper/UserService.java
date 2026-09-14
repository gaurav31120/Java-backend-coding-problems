package com.practice.entitydtomapper;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;

    }


    public UserResponse getUser(int id) {
        User user = userRepository.findById(id);

        if(user == null) {
            return null;
        }

        return userMapper.toResponse(user);



    }


}
