package com.hb.cda.examrest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.Group;

public interface GroupRepository extends JpaRepository<Group, String> {
    Optional<Group> findByName(String name);
    
}
