package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.models.enums.Genre;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.request.DeleteRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private BookRepository bookRepository;


    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void test_That_I_Can_Add_Book() {
        //Given
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("Book Title");
        bookRequest.setAuthor("Author");
        bookRequest.setGenre(Genre.FANTASY);
        bookRequest.setStatus(BookStatus.AVAILABLE);
        bookRequest.setAbout("About book");

        //When
        BookResponse bookResponse = bookServiceImpl.addBook(bookRequest);
        //Then
        assertNotNull(bookResponse);
        Book foundBook = bookRepository.findBookByAuthorAndTitle("Author","Book Title").orElse(null);

        assertNotNull(foundBook);
        assertEquals(bookResponse.getTitle(), foundBook.getTitle());
    }

    @Test
    void test_That_I_Can_Update_Book() {
        //Given
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("Book Title");
        bookRequest.setAuthor("Author");
        bookRequest.setGenre(Genre.FANTASY);
        bookRequest.setStatus(BookStatus.AVAILABLE);
        bookRequest.setAbout("About book");

        bookServiceImpl.addBook(bookRequest);

        //updated_book
        BookRequest updatedBookRequest = new BookRequest();
        updatedBookRequest.setTitle("Book Title");
        updatedBookRequest.setAuthor("Author");
        updatedBookRequest.setGenre(Genre.FANTASY);
        updatedBookRequest.setStatus(BookStatus.UNAVAILABLE);
        updatedBookRequest.setAbout("About book");

        //When
        BookResponse bookResponse = bookServiceImpl.updateBook(updatedBookRequest);

        //Then
        assertNotNull(bookResponse);
        Book foundBook = bookRepository.findBookByAuthorAndTitle("Author","Book Title").orElse(null);
        assertNotNull(foundBook);
        assertEquals(bookResponse.getStatus(), foundBook.getStatus());
    }

    @Test
    void test_That_I_Can_Delete_Book() {
        //Given
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("Book Title");
        bookRequest.setAuthor("Author");
        bookRequest.setGenre(Genre.FANTASY);
        bookRequest.setStatus(BookStatus.AVAILABLE);
        bookRequest.setAbout("About book");

        bookServiceImpl.addBook(bookRequest);
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setBookName("Book Title");
        deleteRequest.setAuthor("Author");
        //When
        BookResponse bookResponse = bookServiceImpl.deleteBook(deleteRequest);
        //Then
        assertNotNull(bookResponse);
        String expectedMessage = "book safely deleted";
        assertEquals(expectedMessage, bookResponse.getMessage());
        Book foundBook = bookRepository.findBookByAuthorAndTitle("Author","Book Title").orElse(null);
        assertNull(foundBook);
    }
  
}