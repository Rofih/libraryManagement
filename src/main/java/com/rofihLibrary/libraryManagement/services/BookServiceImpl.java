package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.dtos.request.BookRequest;
import com.rofihLibrary.libraryManagement.dtos.response.BookResponse;
import com.rofihLibrary.libraryManagement.utils.AlreadyExist;
import com.rofihLibrary.libraryManagement.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rofihLibrary.libraryManagement.utils.BookHelper.isValidBookRequest;

@Service
public class BookServiceImpl implements BookServiceInterface{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookResponse addBook(BookRequest bookRequest) {

        if (isValidBookRequest(bookRequest)) {
            throw new IllegalArgumentException("Invalid book request.  Please check the title and author.");
        }
        boolean status = isBookValid(bookRequest);

        if (!status) {
            Book newBook = BookMapper.mapBook(bookRequest);
            bookRepository.save(newBook);
            return BookMapper.mapBookResponse(newBook);
        } else {
            throw new AlreadyExist("Book already exists");
        }
    }

    private boolean isBookValid(BookRequest bookRequest) {
        Optional<Book> foundBookByTitle = bookRepository.findByTitle(bookRequest.getTitle());
        Optional<Book> foundBookByAuthor = bookRepository.findByAuthor(bookRequest.getAuthor());

        if (foundBookByTitle.isPresent() && foundBookByAuthor.isPresent()) {
            Book foundTitleBook = foundBookByTitle.get();
            Book foundAuthorBook = foundBookByAuthor.get();
            return foundTitleBook.getAuthor().equals(foundAuthorBook.getAuthor()) && foundTitleBook.getTitle().equals(foundAuthorBook.getTitle());
        }
        return false;
    }

    @Override
    public List<Book> viewBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookResponse updateBook(BookRequest bookRequest) {
        if (isValidBookRequest(bookRequest)) {
            throw new IllegalArgumentException("Invalid book request. Please check the title and author.");
        }
        Optional<Book> foundBookByTitle = bookRepository.findByTitle(bookRequest.getTitle());
        if (foundBookByTitle.isPresent()) {
            Book foundBook = foundBookByTitle.get();
            Book newBook = BookMapper.mapBook(bookRequest);
            newBook.setBookId(foundBook.getBookId());
            bookRepository.save(newBook);
            return BookMapper.mapBookResponse(newBook);
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }

    @Override
    public BookResponse deleteBook(BookRequest bookRequest) {
        if (isValidBookRequest(bookRequest)) {
            throw new IllegalArgumentException("Invalid book request. Please check the title and author.");
        }
        Optional<Book> foundBook = bookRepository.findBookByAuthorAndTitle(bookRequest.getAuthor(), bookRequest.getTitle());
        if (foundBook.isPresent()) {
            Book newBook = foundBook.get();
            bookRepository.deleteById(newBook.getBookId());
            return new BookResponse(newBook.getTitle(), BookStatus.UNAVAILABLE,"book safely deleted");
        }
        else {
            throw new IllegalArgumentException("Book not found");
        }
    }

}
