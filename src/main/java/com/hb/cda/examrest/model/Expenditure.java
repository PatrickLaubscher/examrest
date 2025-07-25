package com.hb.cda.examrest.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Expenditure {

    @Id
    @UuidGenerator
    private String id;
    private String description;
    private Double amount;
    
    @ManyToOne
    private Contributor contributor;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy="expenditure")
    List<Repayment> repayments = new ArrayList<>();


    public Expenditure() {
    }

    public Expenditure(String id, String description, Double amount, Contributor contributor, Group group) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.contributor = contributor;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Repayment> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<Repayment> repayments) {
        this.repayments = repayments;
    }



    
    
}
