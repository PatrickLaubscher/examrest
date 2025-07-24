package com.hb.cda.examrest.business.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.User;
import com.hb.cda.examrest.repository.ContributorRepository;
import com.hb.cda.examrest.repository.ExpenditureRepository;
import com.hb.cda.examrest.repository.GroupRepository;
import com.hb.cda.examrest.repository.UserRepository;

@Service
public class ContributorBusinessImpl implements ContributorBusiness {

    private final ContributorRepository contributorRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenditureRepository expenditureRepository;


    public ContributorBusinessImpl(ContributorRepository contributorRepository, GroupRepository groupRepository,
            UserRepository userRepository, ExpenditureRepository expenditureRepository) {
        this.contributorRepository = contributorRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.expenditureRepository = expenditureRepository;
    }


    @Override
    public Contributor addContributor(String email, Integer groupNumber) {

        User user = userRepository.findByEmail(email).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le compte client n'existe pas")
        );

        Group group = groupRepository.findByNumber(groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe n'existe pas")
        );

        Contributor contributor = new Contributor(user.getId(), group.getId(), user, group);
        Contributor newContributor = contributorRepository.save(contributor);
        updateAllContributorsBalance(group);

        return newContributor;
    }


    @Override
    public void removeContributor(Contributor contributor) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }
    
    

    @Override
    public void updateAllContributorsBalance(Group group) {

        // update Contributor balance in the group
        List<Contributor> contributors = contributorRepository.findByGroupNumber(group.getNumber());
        
        for(Contributor groupMember: contributors) {

            List<Expenditure> expenditures = expenditureRepository.findByContributorAndGroup(groupMember, group);
            double totalContributorExpenditures = 0.0;

            for(Expenditure exp: expenditures) {
                totalContributorExpenditures += exp.getAmount();
            }

            double newBalance = totalContributorExpenditures - group.getTotal() / contributors.size();
            groupMember.setBalance(newBalance);
            contributorRepository.save(groupMember);
        }

    }


}
