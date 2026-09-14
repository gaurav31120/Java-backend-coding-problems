package com.practice.userrequestdto;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(101, "Gaurav"));
        users.add(new User(102, "Rahul"));

    }

    public void addUser(User user) {
        users.add(user);
    }

    public int getNextId() {
        int maxId = 0;
        for (User user : users) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;

    }
}
