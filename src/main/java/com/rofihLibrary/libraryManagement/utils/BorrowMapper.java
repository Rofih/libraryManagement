package com.rofihLibrary.libraryManagement.utils;

import com.rofihLibrary.libraryManagement.data.models.Borrowing;
import com.rofihLibrary.libraryManagement.dtos.request.RentRequest;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;

public class BorrowMapper {
    public static Borrowing mapBorrow(RentRequest rentRequest) {
        Borrowing borrowing = new Borrowing();
        borrowing.setBorrowDate(rentRequest.getBorrowDate());
        borrowing.setBook(rentRequest.getBook());
        borrowing.setReturnDate(rentRequest.getReturnDate());
        borrowing.setUser(rentRequest.getUser());
        return borrowing;
    }

    public static RentResponse mapRentResponse(Borrowing borrowing) {
        RentResponse rentResponse = new RentResponse();
        rentResponse.setReturnDate(borrowing.getReturnDate());
        rentResponse.setBookName(borrowing.getBook().getTitle());
        rentResponse.setRentDate(borrowing.getBorrowDate());
        rentResponse.setUserName(borrowing.getUser().getUsername());
        return rentResponse;
    }
}
