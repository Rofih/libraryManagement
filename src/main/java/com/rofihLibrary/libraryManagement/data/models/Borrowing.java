package com.rofihLibrary.libraryManagement.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Borrowing {
    @Id
    private String id;
    private Book book;
    private User user;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
