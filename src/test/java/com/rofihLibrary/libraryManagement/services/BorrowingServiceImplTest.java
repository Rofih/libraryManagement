package com.rofihLibrary.libraryManagement.services;

import com.rofihLibrary.libraryManagement.data.repositries.BorrowingRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BorrowingServiceImplTest {

    @Autowired
    public BorrowingServiceImpl borrowingService;

    @Autowired
    public BorrowingRepository borrowingRepo;


    @AfterEach
    void tearDown(){
        borrowingRepo.deleteAll();
    }



}