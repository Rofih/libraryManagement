package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.Borrowing;
import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.models.enums.Genre;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.data.repositries.BorrowingRepository;
import com.rofihLibrary.libraryManagement.data.repositries.UserRepository;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.request.RentTwoRequest;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BorrowingServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public BorrowingServiceImpl borrowingService;

    @Autowired
    public BorrowingRepository borrowingRepo;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private BookRepository bookRepository;



    @AfterEach
    void tearDown(){
        borrowingRepo.deleteAll();
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }


    @Test
    void test_That_I_Can_Borrow_book() {

        // Given
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("play@example.com");
        userRequest.setPassword("password123");
        userRequest.setUsername("player7");

        // When
        UserResponse userResponse = userService.registerUser(userRequest);

        // Then
        assertNotNull(userResponse);
//        assertEquals("test@example.com", userResponse.);

        User savedUser = userRepository.findByEmail("play@example.com").orElse(null);
        assertNotNull(savedUser);
        assertEquals("play@example.com", savedUser.getEmail());

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


        RentTwoRequest rentTwoRequest = new RentTwoRequest();
        rentTwoRequest.setBookName(bookRequest.getTitle());
        rentTwoRequest.setBookAuthor(bookRequest.getAuthor());
        rentTwoRequest.setUserName(savedUser.getUsername());
        rentTwoRequest.setReturnDate("2025-04-09T10:30:00");

        RentResponse rentResponse = borrowingService.borrowBook(rentTwoRequest);

        assertNotNull(rentResponse);
        Borrowing borrowed = borrowingRepo.findBorrowingByUser(savedUser).orElse(null);

        assertNotNull(borrowed);
        assertEquals(borrowed.getBook().getTitle(), foundBook.getTitle());

    }

}