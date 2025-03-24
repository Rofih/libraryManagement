package com.rofihLibrary.libraryManagement.utils;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;

public class BookMapper {

    public static BookResponse mapBookResponse(Book newBook) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(newBook.getTitle());
        bookResponse.setStatus(newBook.getStatus());
        bookResponse.setMessage("successfully added book");
        return bookResponse;
    }


    public static Book mapBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setStatus(bookRequest.getStatus());
        book.setAuthor(bookRequest.getAuthor());
        book.setGenre(bookRequest.getGenre());
        book.setAbout(bookRequest.getAbout());
        return book;
    }
}
