package com.rofihLibrary.libraryManagement.data.repositries;

import com.rofihLibrary.libraryManagement.data.models.Borrowing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends MongoRepository<Borrowing, String> {
}
