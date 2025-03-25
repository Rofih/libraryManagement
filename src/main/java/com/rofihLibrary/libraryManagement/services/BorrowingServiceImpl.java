package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.Borrowing;
import com.rofihLibrary.libraryManagement.data.models.enums.BookStatus;
import com.rofihLibrary.libraryManagement.data.repositries.BookRepository;
import com.rofihLibrary.libraryManagement.data.repositries.BorrowingRepository;
import com.rofihLibrary.libraryManagement.dtos.request.RentRequest;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;
import com.rofihLibrary.libraryManagement.utils.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingServiceImpl implements BorrowingServiceInterface{

    @Autowired
    public BorrowingRepository borrowingRepo;

    @Autowired
    public BookRepository bookRepo;


public RentResponse borrowBook(RentRequest rentRequest){
    BookStatus bookStatus = bookStatus(rentRequest);
    if (bookStatus == BookStatus.BORROWED ){
        throw new IllegalArgumentException(" book has been borrowed");
    }
    if (bookStatus == BookStatus.UNAVAILABLE){
        throw new IllegalArgumentException(" book is unavailable");
    }
    Borrowing borrowing = BorrowMapper.mapBorrow(rentRequest);
    borrowingRepo.save(borrowing);
    return BorrowMapper.mapRentResponse(borrowing);
}

private BookStatus bookStatus(RentRequest rentRequest){
    Book newBook = rentRequest.getBook();
    Book foundBook = bookRepo.findBookByAuthorAndTitle(newBook.getAuthor(),newBook.getTitle()).orElse(null);
    if (foundBook != null){
        return foundBook.getStatus();
    }
    else {
        throw new IllegalArgumentException("book does not exist");
    }
}
}
