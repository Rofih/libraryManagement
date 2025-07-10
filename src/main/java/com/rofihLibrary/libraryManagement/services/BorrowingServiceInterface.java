package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.dtos.request.BorrowRequest;
import com.rofihLibrary.libraryManagement.dtos.request.RentTwoRequest;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;

public interface BorrowingServiceInterface {
    RentResponse borrowBook(RentTwoRequest borrowRequest);
    RentResponse updateBorrowedBookStatus(BorrowRequest borrowRequest);
}
