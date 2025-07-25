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
        and (:minAmount is null or e.amount >= :minAmount)
        and (:maxAmount is null or e.amount <= :maxAmount)
    """)
    List<Expenditure> findByGroupAndFilters(
        @Param("groupNumber") int groupNumber,
        @Param("firstname") String firstname,
        @Param("lastname") String lastname,
        @Param("minAmount") Double minAmount,
        @Param("maxAmount") Double maxAmount
    );

    List<Expenditure> findByContributorAndGroup(Contributor contributor, Group group);
}
