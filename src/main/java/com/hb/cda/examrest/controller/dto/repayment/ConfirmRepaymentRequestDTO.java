package com.hb.cda.examrest.controller.dto.repayment;

public class ConfirmRepaymentRequestDTO {

    private String email;
    private int groupNumber;
    private Double amount;
    private String payerFirstname;

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
