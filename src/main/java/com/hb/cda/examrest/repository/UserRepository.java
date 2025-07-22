package com.hb.cda.examrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
