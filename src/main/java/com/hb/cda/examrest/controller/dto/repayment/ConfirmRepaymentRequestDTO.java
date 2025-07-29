package com.hb.cda.examrest.controller.dto.repayment;

public class ConfirmRepaymentRequestDTO {

    private int groupNumber;
    private Double amount;
    private String payerFirstname;

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
    public String getPayerFirstname() {
        return payerFirstname;
    }
    public void setPayerFirstname(String payerFirstname) {
        this.payerFirstname = payerFirstname;
    }

    
    
}
