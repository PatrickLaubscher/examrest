package com.hb.cda.examrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;

public interface ExpenditureRepository extends JpaRepository<Expenditure, String> {

    @Query("""
        select e from Expenditure e
        join e.contributor c
        join c.user u
        join e.group g
        where g.number = :groupNumber
        and (:firstname is null or lower(u.firstname) = lower(:firstname))
        and (:lastname is null or lower(u.lastname) = lower(:lastname))
    """)
    List<Expenditure> findByGroupNumberAndOptionalUserName(
        @Param("groupNumber") int groupNumber,
        @Param("firstname") String firstname,
        @Param("lastname") String lastname
    );

    List<Expenditure> findByContributorAndGroup(Contributor contributor, Group group);
}
