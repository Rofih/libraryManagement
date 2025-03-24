package com.rofihLibrary.libraryManagement.data.models;

import java.time.LocalDateTime;

public class Borrowing {
    private String id;
    private Book book;
    private User user;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
