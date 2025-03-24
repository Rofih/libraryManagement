package com.rofihLibrary.libraryManagement.data.repositries;

import com.rofihLibrary.libraryManagement.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
