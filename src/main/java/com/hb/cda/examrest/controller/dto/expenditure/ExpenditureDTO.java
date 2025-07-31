package com.hb.cda.examrest.controller.dto.expenditure;

import com.hb.cda.examrest.controller.dto.contributor.ContributorSingleDTO;
import com.hb.cda.examrest.controller.dto.group.GroupDTO;

public class ExpenditureDTO {

    private double amount;
    private String description;
    private GroupDTO group;
    private ContributorSingleDTO contributor;

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public GroupDTO getGroup() {
        return group;
    }
    public void setGroup(GroupDTO group) {
        this.group = group;
    }
    public ContributorSingleDTO getContributor() {
        return contributor;
    }
    public void setContributor(ContributorSingleDTO contributor) {
        this.contributor = contributor;
    }





    
    
}
