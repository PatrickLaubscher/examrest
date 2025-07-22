package com.hb.cda.examrest.controller.dto;

import jakarta.validation.constraints.NotBlank;


public class CreateContributorDTO {

    @NotBlank
    private String email;
    @NotBlank
    private Integer groupNumber;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getGroupNumber() {
        return groupNumber;
    }
    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    
    
}
