package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.Borrowing;
import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.models.enums.BorrowStatus;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.data.repositries.BorrowingRepository;
import com.rofihLibrary.libraryManagement.data.repositries.UserRepository;
import com.rofihLibrary.libraryManagement.dtos.request.BorrowRequest;
import com.rofihLibrary.libraryManagement.dtos.request.RentRequest;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;
import com.rofihLibrary.libraryManagement.utils.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingServiceInterface {

    @Autowired
    public BorrowingRepository borrowingRepo;

    @Autowired
    public BookRepository bookRepo;

    @Autowired
    public UserRepository userRepository;

    @Override
    public RentResponse borrowBook(BorrowRequest borrowRequest) {
        RentRequest rentRequest = mapRentResponse(borrowRequest);
        if (hasActiveBorrowing(rentRequest.getUser())) {
            throw new IllegalArgumentException("User already has an active borrowing.");
        }

        BookStatus bookStatus = bookStatus(rentRequest);
        if (bookStatus == BookStatus.BORROWED) {
            throw new IllegalArgumentException("Book has been borrowed.");
        }
        if (bookStatus == BookStatus.UNAVAILABLE) {
            throw new IllegalArgumentException("Book is unavailable.");
        }
        if (bookStatus == BookStatus.AVAILABLE) {
            Borrowing borrowing = BorrowMapper.mapBorrow(rentRequest);
            Book newBook = borrowing.getBook();
            newBook.setStatus(BookStatus.BORROWED);
            borrowingRepo.save(borrowing);
            return BorrowMapper.mapRentResponse(borrowing);
        }
        return null;
    }

    private BookStatus bookStatus(RentRequest rentRequest) {
        Book newBook = rentRequest.getBook();
        Book foundBook = bookRepo.findBookByAuthorAndTitle(newBook.getAuthor(), newBook.getTitle()).orElse(null);
        if (foundBook != null) {
            return foundBook.getStatus();
        } else {
            throw new IllegalArgumentException("Book does not exist.");
        }
    }

    private boolean hasActiveBorrowing(User user) {
        List<Borrowing> userBorrowings = borrowingRepo.findByUser(user);
        if (userBorrowings == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        for (Borrowing borrowing : userBorrowings) {
            if (borrowing.getReturnDate().isAfter(now)) {
                return true;
            }
            if (borrowing.getBorrowStatus() == BorrowStatus.YET_TO_RETURN){
                return true;
            }
        }
        return false;
    }

    @Override
    public RentResponse updateBorrowedBookStatus(BorrowRequest borrowRequest) {
        RentRequest rentRequest = mapRentResponse(borrowRequest);
        Book book = rentRequest.getBook();
        Optional<Borrowing> optionalBorrowing = borrowingRepo.findBorrowingByBook(book);

        if (optionalBorrowing.isPresent()) {
            Borrowing borrowing = optionalBorrowing.get();
            borrowing.setBorrowStatus(BorrowStatus.RETURNED);
            borrowingRepo.save(borrowing);

            book.setStatus(BookStatus.AVAILABLE);
            bookRepo.save(book);

            return BorrowMapper.mapRentResponse(borrowing);
        } else {
            throw new IllegalArgumentException("Borrowing record not found for this book.");
        }
    }

    public  RentRequest mapRentResponse(BorrowRequest borrowRequest){
        RentRequest rentRequest = new RentRequest();
        rentRequest.setBorrowDate(LocalDateTime.now());
        rentRequest.setBook(bookRepo.findBookByAuthorAndTitle(borrowRequest.getBookAuthor(), borrowRequest.getBookName()).orElse(null));
        rentRequest.setBorrowDate(borrowRequest.getReturnDate());
        rentRequest.setUser(userRepository.findUserByUsername(borrowRequest.getUserName()).orElse(null));
        return rentRequest;
    }
}