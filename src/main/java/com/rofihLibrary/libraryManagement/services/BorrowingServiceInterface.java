package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.dtos.request.BorrowRequest;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;

public interface BorrowingServiceInterface {
    RentResponse borrowBook(BorrowRequest borrowRequest);
    RentResponse updateBorrowedBookStatus(BorrowRequest borrowRequest);
}
