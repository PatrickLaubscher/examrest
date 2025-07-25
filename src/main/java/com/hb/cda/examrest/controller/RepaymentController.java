package com.hb.cda.examrest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.business.RepaymentBusiness;
import com.hb.cda.examrest.controller.dto.repayment.RepaymentListDTO;
import com.hb.cda.examrest.controller.dto.repayment.RepaymentMapper;
import com.hb.cda.examrest.model.Contributor;
import com.hb.cda.examrest.model.Repayment;




@RestController
@RequestMapping("/api/repayment")
public class RepaymentController {

    private RepaymentBusiness repaymentBusiness;
    private ContributorBusiness contributorBusiness;
    private RepaymentMapper repaymentMapper;

    
    public RepaymentController(RepaymentBusiness repaymentBusiness, ContributorBusiness contributorBusiness,
            RepaymentMapper repaymentMapper) {
        this.repaymentBusiness = repaymentBusiness;
        this.contributorBusiness = contributorBusiness;
        this.repaymentMapper = repaymentMapper;
    }


    @GetMapping("")
    public RepaymentListDTO getRepaymentList(@RequestParam String email, int groupNumber, String type) {

        Contributor contributor = contributorBusiness.findContributor(email, groupNumber);
        RepaymentListDTO repaymentList = new RepaymentListDTO(null);

        if(type.equals("due")) {
            List<Repayment> repayments = repaymentBusiness.findAllDueRepayment(contributor);
            repaymentList = repaymentMapper.toRepaymentListDTO(repayments);

        } else if(type.equals("receiving")) {
            List<Repayment> repayments = repaymentBusiness.findAllIncomingPayment(contributor);
            repaymentList = repaymentMapper.toRepaymentListDTO(repayments);
        }

        return repaymentList;
    }
    
    


}
