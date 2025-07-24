package com.hb.cda.examrest.controller.dto.expenditure;

import com.hb.cda.examrest.controller.dto.contributor.ContributorSingleDTO;

public class ExpenditureDTO {

    private double amount;
    private String description;
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
    public ContributorSingleDTO getContributor() {
        return contributor;
    }
    public void setContributor(ContributorSingleDTO contributor) {
        this.contributor = contributor;
    }


    
    
}
