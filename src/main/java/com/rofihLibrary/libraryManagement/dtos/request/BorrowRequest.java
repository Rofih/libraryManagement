package com.rofihLibrary.libraryManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowRequest {
    private String bookName;
    private String bookAuthor;
    private String userName;
    private LocalDateTime returnDate;
}
