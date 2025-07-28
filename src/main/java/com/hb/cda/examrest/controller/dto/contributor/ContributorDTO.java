package com.hb.cda.examrest.controller.dto.contributor;

import com.hb.cda.examrest.controller.dto.user.UserSingleDTO;

public class ContributorDTO {

    private UserSingleDTO user;
    private Double balance;

    
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public UserSingleDTO getUser() {
        return user;
    }

    public void setUser(UserSingleDTO user) {
        this.user = user;
    }

}
