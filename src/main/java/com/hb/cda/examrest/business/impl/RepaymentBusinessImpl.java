package com.hb.cda.examrest.business.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.business.GroupBusiness;
import com.hb.cda.examrest.business.RepaymentBusiness;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Expenditure;
import com.hb.cda.examrest.model.Group;
import com.hb.cda.examrest.model.Repayment;
import com.hb.cda.examrest.repository.RepaymentRepository;


@Service
public class RepaymentBusinessImpl implements RepaymentBusiness {

    private final RepaymentRepository repaymentRepository;
    private final GroupBusiness groupBusiness;

    
    public RepaymentBusinessImpl(RepaymentRepository repaymentRepository, GroupBusiness groupBusiness) {
        this.repaymentRepository = repaymentRepository;
        this.groupBusiness = groupBusiness;
    }


    @Override
    public Repayment addRepayment(Double amount, int groupNumber, Contributor debtor, Contributor payer, Expenditure expenditure) {

        Group group = groupBusiness.findGroup(groupNumber);

        Repayment newRepayment = new Repayment();
        newRepayment.setPayed(false);
        newRepayment.setAmount(amount);
        newRepayment.setGroup(group);
        newRepayment.setDebtor(debtor);
        newRepayment.setPayer(payer);
        newRepayment.setExpenditure(expenditure);

        return repaymentRepository.save(newRepayment);

    }


    @Override
    public void addAllRepaymentAfterExpenditure(List<Contributor> contributors, Contributor payer, Double amount, int groupNumber, Expenditure expenditure) {

        for(Contributor debtor: contributors) {
            if(!debtor.equals(payer)) {
                Double due = amount / (contributors.size() - 1);
                addRepayment(due, groupNumber, debtor, payer, expenditure);
            }

        }

    }
    
    @Override
    public List<Repayment> findAllDueRepayment(Contributor debtor) {
        return repaymentRepository.findByDebtor(debtor);
    }


    @Override
    public List<Repayment> findAllIncomingPayment(Contributor payer) {
        return repaymentRepository.findByPayer(payer);
    }


    @Override
    public Repayment confirmRepaymentIsPayed(Contributor debtor, int groupNumber, Double amount, Contributor payer) {
        Group group = groupBusiness.findGroup(groupNumber);
        Repayment repayment = repaymentRepository.findByDebtorAndGroupAndAmountAndPayer(debtor, group, amount, payer);

        if(amount.equals(repayment.getAmount())) {
            repayment.setPayed(true);
        } else  {
            throw new IllegalArgumentException("Le montant du remboursement ne correspond pas entièrement à la somme attendue");
        }
       
        return repaymentRepository.save(repayment);
    }


}
