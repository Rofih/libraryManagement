package com.rofihLibrary.libraryManagement.utils;

import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookHelper {

    public static boolean isValidUserRequest(UserRequest userRequest) {
        return userRequest != null &&
                isValidEmail(userRequest.getEmail()) &&
                isValidPassword(userRequest.getPassword());
    }

    public static boolean isValidBookRequest(BookRequest bookRequest) {
        return bookRequest != null &&
                bookRequest.getTitle().isEmpty()&&
                bookRequest.getAuthor().isEmpty();
    }

    private static boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidPassword(String password) {
        return !password.isBlank() && password.length() >= 3;
    }
}
