package com.rofihLibrary.libraryManagement.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralException {

    @ExceptionHandler(AlreadyExist.class)
    public String alreadyExists(AlreadyExist e) {
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
