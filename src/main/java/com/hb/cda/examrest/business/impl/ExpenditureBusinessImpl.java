package com.hb.cda.examrest.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.business.ContributorBusiness;
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
    private final ContributorBusiness contributorBusiness;

    
    public ExpenditureBusinessImpl(ExpenditureRepository expenditureRepository,
            ContributorRepository contributorRepository, GroupRepository groupRepository,
            ContributorBusiness contributorBusiness) {
        this.expenditureRepository = expenditureRepository;
        this.contributorRepository = contributorRepository;
        this.groupRepository = groupRepository;
        this.contributorBusiness = contributorBusiness;
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
        expenditure.setAmount(amount);
        expenditure.setDescription(description);

        Expenditure newExpenditure = expenditureRepository.save(expenditure);
        group.setTotal(group.getTotal() + expenditure.getAmount());
        groupRepository.save(group);
        
        contributorBusiness.updateAllContributorsBalance(group);

        return newExpenditure;
    }


    @Override
    public List<Expenditure> getExpendituresList(int groupNumber, String firstname, String lastname) {

        Group group = groupRepository.findByNumber(groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe n'existe pas")
        );

        List<Expenditure> expenditures = expenditureRepository.findByGroup(group);

        return expenditures.stream()
            .filter(e -> firstname == null || e.getContributor().getUser().getFirstname().equalsIgnoreCase(firstname))
            .filter(e -> lastname == null || e.getContributor().getUser().getLastname().equalsIgnoreCase(lastname))
            .collect(Collectors.toList());

    }

    
    
}
