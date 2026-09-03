package com.practice.usercontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/users")
    public User getUser() {
        User user = new User(101, "Gaurav");
        return user;
    }

    @GetMapping("/users/{id}")
    public User searchUser(@PathVariable int id) {
        User user = new User(id, "Gaurav");
        return user;
    }
}





//    @GetMapping("/users/{id}")
//    public User getUser(@PathVariable int id) {
//        return new User(id, "Gaurav");
//
//
//    }
//
//    @GetMapping("/users")
//    public User searchUser(@RequestParam("name") String name) {
//        return new User(101, name);
//    }




//@GetMapping("/users/{id}")
//public User getUser(@PathVariable int id) {
//    return new User(id, "Gaurav");
//}
//
//@GetMapping("/users")
//public User searchUser(@RequestParam("name") String name) {
//    return new User(101, name);
//}
//}