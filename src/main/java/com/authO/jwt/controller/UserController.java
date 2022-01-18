package com.authO.jwt.controller;

import com.authO.jwt.entity.User;
import com.authO.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@ComponentScan("com.authO.jwt")
@CrossOrigin(allowedHeaders = "*", origins = "*")

public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping({"/getUsers"})
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/searchUser/{username}")
    public User getUserByUsername(@PathVariable String username){
        System.out.println("Get call"+username);
        return userService.getUserByUsername(username);


    }
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return   userService.updateUser(user);
    }
}
