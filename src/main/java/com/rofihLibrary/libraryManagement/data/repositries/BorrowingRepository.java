package com.rofihLibrary.libraryManagement.data.repositries;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.Borrowing;
import com.rofihLibrary.libraryManagement.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends MongoRepository<Borrowing, String> {
    Optional<Borrowing> findBorrowingByBook(Book book);

    List<Borrowing> findByUser(User user);
}
