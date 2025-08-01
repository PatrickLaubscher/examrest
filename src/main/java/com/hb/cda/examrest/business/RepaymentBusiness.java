package com.hb.cda.examrest.business;

import java.util.List;

import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Repayment;


public interface RepaymentBusiness {
    Repayment addRepayment(Double amount, int groupNumber, Contributor debtor, Contributor payer, Expenditure expenditure);
    void addAllRepaymentAfterExpenditure(List<Contributor> contributors, Contributor payer, Double amount, int groupNumber, Expenditure expenditure);
    List<Repayment> findAllDueRepayment(Contributor debtor);
    List<Repayment> findAllIncomingPayment(Contributor payer);
    Repayment confirmRepaymentIsPayed(Contributor debtor, int groupNumber, Double amount, Contributor payer);
}
