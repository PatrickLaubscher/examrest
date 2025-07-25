package com.hb.cda.examrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;

public interface ExpenditureRepository extends JpaRepository<Expenditure, String> {

    List<Expenditure> findByGroup(Group group);
    List<Expenditure> findByContributorAndGroup(Contributor contributor, Group group);
}
