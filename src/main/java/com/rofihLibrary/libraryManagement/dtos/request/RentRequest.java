package com.rofihLibrary.libraryManagement.dtos.request;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class RentRequest {
    public Book book;
    public User user;
    public LocalDateTime borrowDate;
    public LocalDateTime returnDate;
}
