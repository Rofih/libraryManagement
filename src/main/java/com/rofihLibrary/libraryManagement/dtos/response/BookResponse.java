package com.rofihLibrary.libraryManagement.dtos.response;

import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookResponse {
    private String title;
    private BookStatus status;
    private String message;
}
