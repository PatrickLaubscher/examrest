package com.hb.cda.examrest.business.impl;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.repository.ContributorRepository;




public class ContributorBusinessImpl implements ContributorBusiness {

    private final ContributorRepository contributorRepository;


  


    @Override
    public Contributor addContributor(String username, Integer groupNumber) {

        Contributor newContributor = contributorRepository.save(contributor);

        return newContributor;
    }


    @Override
    public void removeContributor(Contributor contributor) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }
    
    
}
