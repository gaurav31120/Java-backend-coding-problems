package com.practice.httpstatuschallenge;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(101, "gaurav"));
        users.add(new User(102, "Rahul"));

    }

    public User findById(int id) {
        for (User user : users) {
            if(user.getId() == id) {
                return user;
            }


        }
        return null;

    }}
