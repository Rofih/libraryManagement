package com.rofihLibrary.libraryManagement.data.repositries;

import com.rofihLibrary.libraryManagement.data.models.Book;
import com.rofihLibrary.libraryManagement.data.models.enums.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book>findByAuthor(String author);
    Optional<Book>findByTitle(String title);
    Optional<Book>findBookByAuthorAndTitle(String author, String title);
    List<Book> findByGenre(Genre genre);
    List<Book> findByAboutContainingIgnoreCase(String keyword);
}
