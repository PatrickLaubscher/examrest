package com.hb.cda.examrest.business;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;

@Service
public interface ContributorBusiness {
    Contributor addContributor(String username, Integer groupNumber); 
    void removeContributor(Contributor contributor);
    void updateAllContributorsBalance(Group group);
}
