package com.rofihLibrary.libraryManagement.utils;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.dtos.request.LoginRequest;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;

public class UserMapper {

    public static User mapUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getUsername());
        user.setRole(userRequest.getRole());
        return user;
    }

    public static UserResponse mapUserResponse(User newUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(newUser.getUserId());
        userResponse.setUserName(newUser.getUsername());
//        userResponse.setMessage("user registered successfully");
        return userResponse;
    }

    public static UserRequest mapUserRequest(LoginRequest loginRequest) {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(loginRequest.getEmail());
        userRequest.setPassword(loginRequest.getPassword());
        return userRequest;
    }
}
