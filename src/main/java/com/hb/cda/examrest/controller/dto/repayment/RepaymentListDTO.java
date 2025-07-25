package com.hb.cda.examrest.controller.dto.repayment;

import java.util.List;

public class RepaymentListDTO {

    private List<RepaymentDTO> repayments;


    public RepaymentListDTO(List<RepaymentDTO> repayments) {
        this.repayments = repayments;
    }

    public List<RepaymentDTO> getRepayments() {
        return repayments;
    }

    public void setRepayments(List<RepaymentDTO> repayments) {
        this.repayments = repayments;
    }




    
}
