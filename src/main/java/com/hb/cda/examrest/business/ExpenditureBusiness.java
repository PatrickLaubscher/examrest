package com.hb.cda.examrest.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.model.Expenditure;

@Service
public interface ExpenditureBusiness {
    Expenditure addExpenditure(String email, int groupNumber, Double amount, String description);
    List<Expenditure> getExpendituresList(int groupNumber, String firstname, String lastname, Double minAmount, Double maxAmount);
}
