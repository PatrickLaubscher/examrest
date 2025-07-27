package com.hb.cda.examrest.business;

import java.util.List;

import com.hb.cda.examrest.model.Expenditure;


public interface ExpenditureBusiness {
    Expenditure addExpenditure(String email, int groupNumber, Double amount, String description);
    List<Expenditure> getExpendituresList(int groupNumber, String firstname, String lastname, Double minAmount, Double maxAmount);
}
