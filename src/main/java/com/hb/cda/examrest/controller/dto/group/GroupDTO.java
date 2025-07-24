package com.hb.cda.examrest.controller.dto.group;

import java.util.List;

import com.hb.cda.examrest.controller.dto.contributor.ContributorDTO;
import com.hb.cda.examrest.controller.dto.expenditure.ExpenditureDTO;

public class GroupDTO {

    private String name;
    private int number;
    private String description;
    private double total;
    private List<ContributorDTO> contributors;
    private List<ExpenditureDTO> expenditures;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public List<ContributorDTO> getContributors() {
        return contributors;
    }
    public void setContributors(List<ContributorDTO> contributors) {
        this.contributors = contributors;
    }
    public List<ExpenditureDTO> getExpenditures() {
        return expenditures;
    }
    public void setExpenditures(List<ExpenditureDTO> expenditures) {
        this.expenditures = expenditures;
    }


    
}
