package com.rofihLibrary.libraryManagement.controllers;

import com.rofihLibrary.libraryManagement.data.repositries.BorrowingRepository;
import com.rofihLibrary.libraryManagement.dtos.request.BorrowRequest;
import com.rofihLibrary.libraryManagement.dtos.response.RentResponse;
import com.rofihLibrary.libraryManagement.services.BorrowingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rent_books")
public class BorrowingController {

    @Autowired
    public BorrowingServiceInterface borrowingServiceInterface;

    @PostMapping("/borrow_book")
    public RentResponse borrowBook(@RequestBody BorrowRequest borrowRequest){
        return borrowingServiceInterface.borrowBook(borrowRequest);
    }

    @PostMapping("/update_status")
    public RentResponse updateBorrowStatus(@RequestBody BorrowRequest borrowRequest){
        return borrowingServiceInterface.updateBorrowedBookStatus(borrowRequest);
    }

}
