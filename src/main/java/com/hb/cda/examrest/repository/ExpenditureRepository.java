package com.hb.cda.examrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, String> {
    
}
