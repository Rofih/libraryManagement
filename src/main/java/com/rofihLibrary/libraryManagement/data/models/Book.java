package com.rofihLibrary.libraryManagement.data.models;

import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.models.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    private String bookId;
    private String title;
    private String author;
    private Genre genre;
    private BookStatus status;
    private String about;
}
