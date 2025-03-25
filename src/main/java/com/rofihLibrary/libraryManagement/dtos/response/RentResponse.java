package com.rofihLibrary.libraryManagement.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentResponse {
    private String bookName;
    private String userName;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
}
