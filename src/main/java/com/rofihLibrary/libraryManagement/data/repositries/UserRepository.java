package com.rofihLibrary.libraryManagement.data.repositries;

import com.rofihLibrary.libraryManagement.data.models.User;
import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User>getUsersByRole(Role role);
}
