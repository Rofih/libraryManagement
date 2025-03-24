package com.rofihLibrary.libraryManagement.dtos.response;

import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewBookResponse {
    private String title;
    private String author;
    private BookStatus status;
}
