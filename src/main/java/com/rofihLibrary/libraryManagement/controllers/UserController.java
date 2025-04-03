package com.rofihLibrary.libraryManagement.controllers;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import com.rofihLibrary.libraryManagement.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController("/user")
public class UserController {

    @Autowired
    public UserServiceInterface userServiceInterface;


    @PostMapping("/user/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userServiceInterface.registerUser(userRequest);
    }

    @PostMapping("/user/login")
    public UserResponse logIn(@RequestBody UserRequest userRequest){
        return userServiceInterface.loginUser(userRequest);
    }

    @PostMapping("/user/get_by_role/{role}")
    public List<User>getUsersByRole(@PathVariable("role") Role role){
        return userServiceInterface.getUserByRole(role);
    }
}
