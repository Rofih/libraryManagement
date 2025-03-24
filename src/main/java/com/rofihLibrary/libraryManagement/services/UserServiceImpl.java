package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.data.repositries.UserRepository;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.request.UserRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;
import com.rofihLibrary.libraryManagement.dtos.response.UserResponse;
import com.rofihLibrary.libraryManagement.utils.BookMapper;
import com.rofihLibrary.libraryManagement.utils.AlreadyExist;
import com.rofihLibrary.libraryManagement.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

public UserResponse registerUser(UserRequest userRequest) {
    User newUser = UserMapper.mapUser(userRequest);
    UserResponse newUserResponse = UserMapper.mapUserResponse(newUser);
    if (!userRepository.existsByEmail(userRequest.getEmail())) {
       userRepository.save(newUser);
       return newUserResponse;
    }
    else {
        throw  new AlreadyExist("user already exists");
    }

}
public UserResponse loginUser(UserRequest userRequest) {
    Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

    if (userOptional.isPresent()) {
        User foundUser = userOptional.get();
        boolean isCorrect = foundUser.getPassword().equals(userRequest.getPassword());

        if (isCorrect) {
            return UserMapper.mapUserResponse(foundUser);
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    } else {
        throw new IllegalArgumentException("User not found");
    }
}

public BookResponse addBook(BookRequest bookRequest) {
    boolean status = isBookValid(bookRequest);

    if (status == false) {
        Book newBook = BookMapper.mapBook(bookRequest);
        bookRepository.save(newBook);
        return BookMapper.mapBookResponse(newBook);
    }
    else {
        throw new AlreadyExist("book already exists");
    }
}
private boolean isBookValid(BookRequest bookRequest) {
    Optional<Book> foundBookByTitle = bookRepository.findByTitle(bookRequest.getTitle());
    Optional<Book> foundBookByAuthor = bookRepository.findByAuthor(bookRequest.getAuthor());
    if (foundBookByTitle.isPresent() && foundBookByAuthor.isPresent()) {
        Book foundTitleBook = foundBookByTitle.get();
        Book foundAuthorBook = foundBookByAuthor.get();
        if (foundTitleBook.getAuthor().equals(foundAuthorBook.getAuthor()) && (!foundTitleBook.getTitle().equals(foundTitleBook.getTitle()))) {
            return false;
        } else if (!foundTitleBook.getAuthor().equals(foundAuthorBook.getAuthor()) && (foundTitleBook.getTitle().equals(foundTitleBook.getTitle()))) {
            return false;
        }
    }

        return false;
}

public List<Book>viewBooks() {
    return bookRepository.findAll();
}

public BookResponse updateBook(BookRequest bookRequest) {
    Optional<Book> foundBookByTitle = bookRepository.findByTitle(bookRequest.getTitle());

}
}
