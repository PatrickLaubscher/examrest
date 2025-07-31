package com.hb.cda.examrest.controller.dto.expenditure;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateExpenditureDTO {

    @Min(1)
    private int groupNumber;
    @Min(1)
    private Double amount;
    @NotBlank
    @Size(min=3, max=64)
    private String description;


    public int getGroupNumber() {
        return groupNumber;
    }
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }




    
}
