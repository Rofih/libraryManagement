package com.rofihLibrary.libraryManagement.controllers;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import com.rofihLibrary.libraryManagement.dtos.request.LoginRequest;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import com.rofihLibrary.libraryManagement.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173" )
@RestController("/user")
public class UserController {

    @Autowired
    public UserServiceInterface userServiceInterface;


    @PostMapping("/user/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userServiceInterface.registerUser(userRequest);
    }

    @PostMapping("/user/login")
    public UserResponse logIn(@RequestBody LoginRequest userRequest){
        return userServiceInterface.loginUser(userRequest);
    }

    @PostMapping("/user/get_by_role/{role}")
    public List<User>getUsersByRole(@PathVariable("role") Role role){
        return userServiceInterface.getUserByRole(role);
    }
}
