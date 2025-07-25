package com.hb.cda.examrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.ExpenditureBusiness;
import com.hb.cda.examrest.controller.dto.expenditure.CreateExpenditureDTO;
import com.hb.cda.examrest.controller.dto.group.GroupDTO;
import com.hb.cda.examrest.model.Expenditure;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/expenditure")
public class ExpenditureController {

    private final ExpenditureBusiness expenditureBusiness;

    public ExpenditureController(ExpenditureBusiness expenditureBusiness) {
        this.expenditureBusiness = expenditureBusiness;
    }


    @PostMapping("/add")
    public String addExpenditure(@RequestBody @Valid CreateExpenditureDTO dto) {

        Expenditure expenditure = expenditureBusiness.addExpenditure(dto.getEmail(), dto.getGroupNumber(), dto.getAmount(), dto.getDescription());

            return "Nouvelle dépense de " + expenditure.getAmount() + "€";
    }
    

    @GetMapping("")
    public ExpenditureListDTO getGroupById(
        @RequestParam int groupNumber,
        @RequestParam(required = false) String firstname, 
        @RequestParam(required = false) String lastname
        ) {
        return groupMapper.toDTO(groupBusiness.findGroup(groupNumber));
    }
    
}
