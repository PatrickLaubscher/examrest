package com.hb.cda.examrest.controller.dto.expenditure;

import java.util.List;

public class ExpenditureListDTO {

    private List<ExpenditureDTO> expenditures;

    public ExpenditureListDTO(List<ExpenditureDTO> expenditures) {
        this.expenditures = expenditures;
    }

    public List<ExpenditureDTO> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(List<ExpenditureDTO> expenditures) {
        this.expenditures = expenditures;
    }
    
}
