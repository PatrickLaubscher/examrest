package com.hb.cda.examrest.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Repayment {

    @Id
    @UuidGenerator
    private String id;
    private Double amount;
    
    @Column(name="is_payed")
    private Boolean isPayed;

    @ManyToOne
    private Group group;

    @ManyToOne
    private Contributor debtor;

    @ManyToOne
    private Contributor payer;

    @ManyToOne
    private Expenditure expenditure;


    
    public Repayment() {
    }

    public Repayment(String id, Double amount, Boolean isPayed, Group group, Contributor debtor, Contributor payer) {
        this.id = id;
        this.amount = amount;
        this.isPayed = isPayed;
        this.group = group;
        this.debtor = debtor;
        this.payer = payer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Contributor getDebtor() {
        return debtor;
    }

    public void setDebtor(Contributor debtor) {
        this.debtor = debtor;
    }

    public Contributor getPayer() {
        return payer;
    }

    public void setPayer(Contributor payer) {
        this.payer = payer;
    }

    public Expenditure getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Expenditure expenditure) {
        this.expenditure = expenditure;
    }







}
