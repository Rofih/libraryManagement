package com.rofihLibrary.libraryManagement.controllers;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;
import com.rofihLibrary.libraryManagement.services.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/book")
public class BookController {

    @Autowired
    public BookServiceInterface bookServiceInterface;

    @PostMapping("/add_book")
    public BookResponse addBook(@RequestBody BookRequest bookRequest){
        return bookServiceInterface.addBook(bookRequest);
    }

    @PostMapping("/update_book")
    public BookResponse updateBook(@RequestBody BookRequest bookRequest){
        return bookServiceInterface.updateBook(bookRequest);
    }

    @PostMapping("/delete_book")
    public BookResponse deleteBook(@RequestBody BookRequest bookRequest){
        return bookServiceInterface.deleteBook(bookRequest);
    }

    @GetMapping("/view_books")
    public List<Book> viewAllBooks(){
        return bookServiceInterface.viewBooks();
    }
}
