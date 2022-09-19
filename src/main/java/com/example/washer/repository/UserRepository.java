package com.example.washer.repository;

import com.example.washer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String userName);
    boolean existsByUsername(String userName);
}
