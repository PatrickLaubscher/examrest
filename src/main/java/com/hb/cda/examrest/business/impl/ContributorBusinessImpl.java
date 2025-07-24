package com.hb.cda.examrest.business.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.User;
import com.hb.cda.examrest.repository.ContributorRepository;
import com.hb.cda.examrest.repository.GroupRepository;
import com.hb.cda.examrest.repository.UserRepository;

@Service
public class ContributorBusinessImpl implements ContributorBusiness {

    private final ContributorRepository contributorRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;


    public ContributorBusinessImpl(ContributorRepository contributorRepository, GroupRepository groupRepository,
            UserRepository userRepository) {
        this.contributorRepository = contributorRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Contributor addContributor(String email, Integer groupNumber) {

        User user = userRepository.findByEmail(email).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le compte client n'existe pas")
        );

        Group group = groupRepository.findByNumber(groupNumber).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe n'existe pas")
        );


        Double balance = 0.0; // to be updated with the balance calculation

        Contributor contributor = new Contributor(user.getId(), group.getId(), balance, user, group);
        Contributor newContributor = contributorRepository.save(contributor);

        return newContributor;
    }


    @Override
    public void removeContributor(Contributor contributor) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }
    
    
}
