package com.rofihLibrary.libraryManagement.services;

import static org.junit.jupiter.api.Assertions.*;

import com.rofihLibrary.libraryManagement.utils.AlreadyExist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import com.rofihLibrary.libraryManagement.data.repositries.UserRepository;



@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;



    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    void testThatICanRegisterA_User() {
        // Given
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // When
        UserResponse userResponse = userService.registerUser(userRequest);

        // Then
        assertNotNull(userResponse);
//        assertEquals("test@example.com", userResponse.);

        User savedUser = userRepository.findByEmail("test@example.com").orElse(null);
        assertNotNull(savedUser);
        assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    void testThatRegistered_User_alreadyExists() {
        // Given
        UserRequest userRequest1 = new UserRequest();
        userRequest1.setEmail("test2@example.com");
        userRequest1.setPassword("password123");
        userService.registerUser(userRequest1);

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setEmail("test2@example.com");
        userRequest2.setPassword("anotherpassword");

        // When
        Exception exception = assertThrows(AlreadyExist.class, () ->      {
            userService.registerUser(userRequest2);
        });

        // Then
        assertEquals("User already exists", exception.getMessage());
    }

    @Test
    void testThat_I_Can_A_LoginUser() {
        // Given
        UserRequest registerRequest = new UserRequest();
        registerRequest.setEmail("login@example.com");
        registerRequest.setPassword("loginpassword");
        userService.registerUser(registerRequest);

        UserRequest loginRequest = new UserRequest();
        loginRequest.setEmail("login@example.com");
        loginRequest.setPassword("loginpassword");

        // When
        UserResponse loginResponse = userService.loginUser(loginRequest);

        // Then
        assertNotNull(loginResponse);
//        assertEquals("login@example.com", loginResponse.);
    }

    @Test
    void testLoginUser_invalidPassword() {
        // Given
        UserRequest registerRequest = new UserRequest();
        registerRequest.setEmail("loginfail@example.com");
        registerRequest.setPassword("correctpassword");
        userService.registerUser(registerRequest);

        UserRequest loginRequest = new UserRequest();
        loginRequest.setEmail("loginfail@example.com");
        loginRequest.setPassword("wrongpassword");

        //When
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            userService.loginUser(loginRequest);
        });

        //Then
        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    void testLoginUser_userNotFound() {
        //Given
        UserRequest loginRequest = new UserRequest();
        loginRequest.setEmail("doesnotexist@example.com");
        loginRequest.setPassword("anyPassword");

        //When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.loginUser(loginRequest);
        });

        //Then
        assertEquals("User not found", exception.getMessage());
    }
}