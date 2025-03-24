package com.rofihLibrary.libraryManagement.data.models;

import com.rofihLibrary.libraryManagement.data.models.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private String bookId;
    private String title;
    private String author;
    private Genre genre;
}
