package com.hb.cda.examrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.ContributorId;

public interface ContributorRepository extends JpaRepository<Contributor, ContributorId> {
    
}
