package com.rofihLibrary.libraryManagement.data.models;

import com.rofihLibrary.libraryManagement.data.models.enums.BorrowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("borrowed_book")
public class Borrowing {
    @Id
    private String id;
    private Book book;
    private User user;
    private BorrowStatus borrowStatus;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
