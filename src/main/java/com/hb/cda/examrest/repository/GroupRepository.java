package com.hb.cda.examrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hb.cda.examrest.model.Group;

public interface GroupRepository extends JpaRepository<Group, String> {

    Optional<Group> findByName(String name);

    Optional<Group> findByNumber(Integer number);

    @Query("SELECT MAX(g.number) FROM Group g")
    Optional<Integer> findMaxNumero();
    
}
