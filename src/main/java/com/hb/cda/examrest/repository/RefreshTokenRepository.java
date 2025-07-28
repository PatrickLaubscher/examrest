package com.hb.cda.examrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hb.cda.examrest.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    @Modifying
    @Query("DELETE FROM RefreshToken r WHERE r.expiresAt < NOW()")
    void deleteExpired();

    
}
