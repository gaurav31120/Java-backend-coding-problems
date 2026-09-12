package com.practice.postuser;

import org.springframework.stereotype.Service;

@Service
public class UserService {

        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository= userRepository;
        }

        public User createUser(User user) {
            userRepository.addUser(user);
            return user;
        }
}
