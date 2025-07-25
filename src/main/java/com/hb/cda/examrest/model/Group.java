package com.hb.cda.examrest.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "group_table")
public class Group {
    
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String description;
    private Integer number;
    private double total;

    @OneToMany(mappedBy="group")
    List<Contributor> contributors = new ArrayList<>();

    @OneToMany(mappedBy="group")
    List<Expenditure> expenditures = new ArrayList<>();

    @OneToMany(mappedBy="group")
    List<Expenditure> repayments = new ArrayList<>();


    public Group() {
    }

    public Group(String id, String name, String description, Integer number, double total) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.number = number;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public List<Expenditure> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(List<Expenditure> expenditures) {
        this.expenditures = expenditures;
    }

    public List<Expenditure> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<Expenditure> repayments) {
        this.repayments = repayments;
    }




}
