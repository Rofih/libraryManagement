package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.request.DeleteRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;

import java.util.List;

public interface BookServiceInterface {
    BookResponse addBook(BookRequest bookRequest);
    BookResponse updateBook(BookRequest bookRequest);
    List<Book> viewBooks();
    BookResponse deleteBook(DeleteRequest bookRequest);
}
