package com.practice.userresponsedto;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(101, "Gaurav", "gaurav@gmail.com"));
        users.add(new User(102, "Priya", "priya@gmail.com"));
    }

    public User findById(int id) {
        for(User user: users) {
            if(id == user.getId()) {
                return user;
            }
        }
        return null;
    }

}
