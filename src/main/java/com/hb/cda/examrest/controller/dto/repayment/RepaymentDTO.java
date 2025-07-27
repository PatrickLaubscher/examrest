package com.hb.cda.examrest.controller.dto.repayment;

import com.hb.cda.examrest.controller.dto.expenditure.ExpenditureDTO;


public class RepaymentDTO {
    
    private ExpenditureDTO expenditure;    
    private Double amount;
    private Boolean payed;


    public ExpenditureDTO getExpenditure() {
        return expenditure;
    }
    public void setExpenditure(ExpenditureDTO expenditure) {
        this.expenditure = expenditure;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Boolean getPayed() {
        return payed;
    }
    public void setPayed(Boolean payed) {
        this.payed = payed;
    }


 

    

}
