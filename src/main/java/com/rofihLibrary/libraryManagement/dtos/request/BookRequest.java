package com.rofihLibrary.libraryManagement.dtos.request;

import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.models.enums.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookRequest {
    private String title;
    private String author;
    private String about;
    private BookStatus status;
    private Genre genre;
}
