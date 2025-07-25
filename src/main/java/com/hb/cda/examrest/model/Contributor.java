package com.hb.cda.examrest.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
@IdClass(ContributorId.class)
public class Contributor {

    @Id 
    @Column(name="user_id")
    private String userId;

    @Id 
    @Column(name="group_id")
    private String groupId;

    private Double balance;

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @jakarta.persistence.JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Group group;


    @OneToMany(mappedBy="contributor")
    private List<Expenditure> expenditures = new ArrayList<>();

    @OneToMany(mappedBy="debtor")
    private List<Repayment> repaymentsDue = new ArrayList<>();

    @OneToMany(mappedBy="payer")
    private List<Repayment> refunds = new ArrayList<>();



    public Contributor() {
    }


    public Contributor(String userId, String groupId, User user, Group group) {
        this.userId = userId;
        this.groupId = groupId;
        this.balance = 0.0;
        this.user = user;
        this.group = group;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getGroupId() {
        return groupId;
    }


    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public Double getBalance() {
        return balance;
    }


    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Group getGroup() {
        return group;
    }


    public void setGroup(Group group) {
        this.group = group;
    }


    public List<Expenditure> getExpenditures() {
        return expenditures;
    }


    public void setExpenditures(List<Expenditure> expenditures) {
        this.expenditures = expenditures;
    }


    public List<Repayment> getRepaymentsDue() {
        return repaymentsDue;
    }


    public void setRepaymentsDue(List<Repayment> repaymentsDue) {
        this.repaymentsDue = repaymentsDue;
    }


    public List<Repayment> getRefunds() {
        return refunds;
    }


    public void setRefunds(List<Repayment> refunds) {
        this.refunds = refunds;
    }


}
