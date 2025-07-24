package com.hb.cda.examrest.controller.dto.expenditure;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateExpenditureDTO {

    @NotBlank
    private String email;
    @Min(1)
    private int groupNumber;
    @Min(1)
    private double amount;
    @NotBlank
    @Size(min=3, max=64)
    private String description;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getGroupNumber() {
        return groupNumber;
    }
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
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




    
}
