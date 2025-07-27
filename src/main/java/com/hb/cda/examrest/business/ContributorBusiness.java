package com.hb.cda.examrest.business;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Group;


public interface ContributorBusiness {
    Contributor addContributor(String username, int groupNumber);
    Contributor findContributor(String email, int groupNumber);
    Contributor findContributorByFirstname(String firstname, int groupNumber);
    void removeContributor(Contributor contributor);
    void updateAllContributorsBalance(Group group);
}
