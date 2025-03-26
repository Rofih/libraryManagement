package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.data.repositries.UserRepository;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import com.rofihLibrary.libraryManagement.utils.AlreadyExist;
import com.rofihLibrary.libraryManagement.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.rofihLibrary.libraryManagement.utils.BookHelper.isValidUserRequest;


@Service
public class UserServiceImpl implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;



    public UserResponse registerUser(UserRequest userRequest) {
        if (!isValidUserRequest(userRequest)) {
            UserResponse response = new UserResponse();
            response.setMessage("Haa! Please make sure your email and password dey okay.");
            return response;
        }

//        User newUser = UserMapper.mapUser(userRequest);
//        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(hashedPassword);


        User newUser = UserMapper.mapUser(userRequest);
        UserResponse newUserResponse = UserMapper.mapUserResponse(newUser);
        if (!userRepository.existsByEmail(userRequest.getEmail())) {
            userRepository.save(newUser);
            newUserResponse.setMessage("your registered successfully");
            return newUserResponse;
        } else {
            throw new AlreadyExist("User already exists");
        }
    }

    public UserResponse loginUser(UserRequest userRequest) {
        if (!isValidUserRequest(userRequest)) {
            throw new IllegalArgumentException("Invalid user request.  Please check your email and password.");
        }

        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();
            boolean isCorrect = foundUser.getPassword().equals(userRequest.getPassword());

            if (isCorrect) {
                return UserMapper.mapUserResponse(foundUser);
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<User> getUserByRole(Role role) {
        return userRepository.getUsersByRole(role);
    }




}

