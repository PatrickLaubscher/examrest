package com.hb.cda.examrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.Repayment;

public interface RepaymentRepository extends JpaRepository<Repayment, String>{

    List<Repayment> findByDebtor(Contributor debtor);
    List<Repayment> findByPayer(Contributor payer);
    Repayment findByDebtorAndGroupAndPayer(Contributor debtor, Group group, Contributor payer);
}
