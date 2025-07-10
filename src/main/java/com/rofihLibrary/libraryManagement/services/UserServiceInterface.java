package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import com.rofihLibrary.libraryManagement.dtos.request.LoginRequest;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
//import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface UserServiceInterface {
    UserResponse registerUser(UserRequest userRequest) ;
    UserResponse loginUser(LoginRequest loginRequest) ;
    List<User> getUserByRole(Role role);
//    UserDetails loadUserByUsername(String username);
}
