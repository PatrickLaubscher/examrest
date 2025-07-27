package com.hb.cda.examrest.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.business.ExpenditureBusiness;
import com.hb.cda.examrest.business.RepaymentBusiness;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.repository.ContributorRepository;
import com.hb.cda.examrest.repository.ExpenditureRepository;
import com.hb.cda.examrest.repository.GroupRepository;

import jakarta.transaction.Transactional;

@Service
public class ExpenditureBusinessImpl implements ExpenditureBusiness {

    private final ExpenditureRepository expenditureRepository;
    private final ContributorRepository contributorRepository;
    private final GroupRepository groupRepository;
    private final ContributorBusiness contributorBusiness;
    private final RepaymentBusiness repaymentBusiness;

    @Autowired
    public ExpenditureBusinessImpl(ExpenditureRepository expenditureRepository,
            ContributorRepository contributorRepository, GroupRepository groupRepository,
            ContributorBusiness contributorBusiness, RepaymentBusiness repaymentBusiness) {
        this.expenditureRepository = expenditureRepository;
        this.contributorRepository = contributorRepository;
        this.groupRepository = groupRepository;
        this.contributorBusiness = contributorBusiness;
        this.repaymentBusiness = repaymentBusiness;
    }


    @Override
    public Expenditure addExpenditure(String email, int groupNumber, Double amount, String description) {

        Contributor payer = contributorRepository.findByEmailAndGroupNumber(email, groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le contributeur n'existe pas pour ce groupe")
        );

        Group group = groupRepository.findByNumber(groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe n'existe pas")
        );
        
        Expenditure expenditure = new Expenditure();

        expenditure.setContributor(payer);
        expenditure.setGroup(group);
        expenditure.setAmount(amount);
        expenditure.setDescription(description);

        Expenditure newExpenditure = expenditureRepository.save(expenditure);
        group.setTotal(group.getTotal() + expenditure.getAmount());
        groupRepository.save(group);
        
        contributorBusiness.updateAllContributorsBalance(group);

        List<Contributor> contributors = contributorRepository.findByGroupNumber(groupNumber);
        repaymentBusiness.addAllRepaymentAfterExpenditure(contributors, payer, amount, groupNumber, newExpenditure);

        return newExpenditure;
    }


    @Override
    @Transactional
    public List<Expenditure> getExpendituresList(int groupNumber, String firstname, String lastname, Double minAmount, Double maxAmount) {

        return expenditureRepository.findByGroupAndFilters(groupNumber, firstname, lastname, minAmount, maxAmount);

    }

    
    
}
