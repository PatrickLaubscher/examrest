package com.hb.cda.examrest.business.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.business.ExpenditureBusiness;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.repository.ContributorRepository;
import com.hb.cda.examrest.repository.ExpenditureRepository;
import com.hb.cda.examrest.repository.GroupRepository;

@Service
public class ExpenditureBusinessImpl implements ExpenditureBusiness {

    private final ExpenditureRepository expenditureRepository;
    private final ContributorRepository contributorRepository;
    private final GroupRepository groupRepository;


    public ExpenditureBusinessImpl(ExpenditureRepository expenditureRepository,
            ContributorRepository contributorRepository, GroupRepository groupRepository) {
        this.expenditureRepository = expenditureRepository;
        this.contributorRepository = contributorRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public Expenditure addExpenditure(String email, int groupNumber, double amount, String description) {

        Contributor contributor = contributorRepository.findByUserEmailAndGroupNumber(email, groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le contributeur n'existe pas pour ce groupe")
        );

        Group group = groupRepository.findByNumber(groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe n'existe pas")
        );
        
        Expenditure expenditure = new Expenditure();

        expenditure.setContributor(contributor);
        expenditure.setGroup(group);
        expenditure.setAmont(amount);
        expenditure.setDescription(description);

        Expenditure newExpenditure = expenditureRepository.save(expenditure);
        group.setTotal(group.getTotal() + expenditure.getAmont());
        
        // update Contributor balance in the group

        return newExpenditure;
    }

    
    
}
