package com.hb.cda.examrest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.ExpenditureBusiness;
import com.hb.cda.examrest.controller.dto.expenditure.CreateExpenditureDTO;
import com.hb.cda.examrest.controller.dto.expenditure.ExpenditureDTO;
import com.hb.cda.examrest.controller.dto.expenditure.ExpenditureMapper;
import com.hb.cda.examrest.model.Expenditure;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/expenditure")
public class ExpenditureController {

    private final ExpenditureBusiness expenditureBusiness;
    private final ExpenditureMapper expenditureMapper;

    
    public ExpenditureController(ExpenditureBusiness expenditureBusiness, ExpenditureMapper expenditureMapper) {
        this.expenditureBusiness = expenditureBusiness;
        this.expenditureMapper = expenditureMapper;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenditureDTO addExpenditure(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid CreateExpenditureDTO dto) {
        String email = userDetails.getUsername();
        Expenditure expenditure = expenditureBusiness.addExpenditure(email, dto.getGroupNumber(), dto.getAmount(), dto.getDescription());
        return expenditureMapper.toDTO(expenditure);
    }
    

    @GetMapping("")
    public List<ExpenditureDTO> getGroupById(
        @RequestParam int groupNumber,
        @RequestParam(required = false) String firstname, 
        @RequestParam(required = false) String lastname,
        @RequestParam(required = false) Double min,
        @RequestParam(required = false) Double max
        ) {
        List<Expenditure> expenditures = expenditureBusiness.getExpendituresList(groupNumber, firstname, lastname, min, max);
        return expenditureMapper.toDTOList(expenditures);
    }
    
}
