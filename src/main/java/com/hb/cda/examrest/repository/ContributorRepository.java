package com.hb.cda.examrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.ContributorId;

public interface ContributorRepository extends JpaRepository<Contributor, ContributorId> {

    Optional<Contributor> findByUserEmailAndGroupNumber(String email, Integer number);

    List<Contributor> findByGroupNumber(Integer number);
    
}
