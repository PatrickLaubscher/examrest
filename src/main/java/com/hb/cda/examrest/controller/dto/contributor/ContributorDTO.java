package com.hb.cda.examrest.controller.dto.contributor;

import com.hb.cda.examrest.controller.dto.user.UserDTO;

public class ContributorDTO {

    private UserDTO user;
    private Double balance;

    
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
