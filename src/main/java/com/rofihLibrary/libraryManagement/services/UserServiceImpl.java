package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.data.repositries.UserRepository;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import com.rofihLibrary.libraryManagement.utils.AlreadyExist;
//import com.rofihLibrary.libraryManagement.utils.JwtUtil;
import com.rofihLibrary.libraryManagement.utils.PasswordHashingService;
import com.rofihLibrary.libraryManagement.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static com.rofihLibrary.libraryManagement.utils.BookHelper.isValidUserRequest;


@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private  UserRepository userRepository;


//    private PasswordEncoder passwordEncoder;
//    private final BookRepository bookRepository;
//    private final JwtUtil jwtUtil;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//    }

//    @Autowired
//    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }


    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        if (!isValidUserRequest(userRequest)) {
            UserResponse response = new UserResponse();
            response.setMessage("Haa! Please make sure your email and password dey okay.");
            return response;
        }

        User newUser = UserMapper.mapUser(userRequest);
//        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(hashedPassword);

        String hashedPassword = PasswordHashingService.hashPassword(newUser.getPassword());
        newUser.setPassword(hashedPassword);


        UserResponse newUserResponse = UserMapper.mapUserResponse(newUser);
        if (!userRepository.existsByEmail(userRequest.getEmail())) {
            userRepository.save(newUser);
            newUserResponse.setMessage("your registered successfully");
            return newUserResponse;
        } else {
            throw new AlreadyExist("User already exists");
        }
    }
    @Override
    public UserResponse loginUser(UserRequest userRequest) {
        if (!isValidUserRequest(userRequest)) {
            throw new IllegalArgumentException("Invalid user request.  Please check your email and password.");
        }

        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();
//            passwordEncoder.matches(userRequest.getPassword(), foundUser.getPassword())
            if (PasswordHashingService.checkPassword(userRequest.getPassword(),foundUser.getPassword())) {
//                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                        foundUser.getUsername(),
//                        foundUser.getPassword(),
//                        new ArrayList<>()
//                );
//                String token = jwtUtil.generateToken(userDetails);
                UserResponse response = UserMapper.mapUserResponse(foundUser);
                response.setMessage("Login successful!");
//                response.setToken(token);
                return response;
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
    @Override
    public List<User> getUserByRole(Role role) {
        return userRepository.getUsersByRole(role);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findUserByUsername(username);
//        return userOptional.map(user -> new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                new ArrayList<>()
//        )).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//    }


}

