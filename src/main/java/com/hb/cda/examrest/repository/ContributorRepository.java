package com.hb.cda.examrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.ContributorId;

public interface ContributorRepository extends JpaRepository<Contributor, ContributorId> {

    @Query("SELECT c FROM Contributor c WHERE c.user.email = :email AND c.group.number = :groupNumber")
    Optional<Contributor> findByEmailAndGroupNumber(@Param("email") String email, @Param("groupNumber") int groupNumber);

    @Query("SELECT c FROM Contributor c WHERE c.user.firstname = :firstname AND c.group.number = :groupNumber")
    Optional<Contributor> findByFirstnameAndGroupNumber(@Param("firstname") String firstname,  @Param("groupNumber") int groupNumber);

    List<Contributor> findByGroupNumber(int number);
    
}
