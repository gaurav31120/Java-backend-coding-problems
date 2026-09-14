package com.practice.layereduserapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(101,"Gaurav"));
        users.add(new User(102,"Rahul"));
    }

    public List<User> getAllUsers() {
        return users;
    }

}
