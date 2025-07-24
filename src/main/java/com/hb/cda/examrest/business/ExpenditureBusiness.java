package com.hb.cda.examrest.business;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.model.Expenditure;

@Service
public interface ExpenditureBusiness {
    Expenditure addExpenditure(String email, int groupNumber, double amount, String description);
}
