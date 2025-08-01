package com.hb.cda.examrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.controller.dto.contributor.CreateContributorDTO;


@RestController
@RequestMapping("/api/contributor")
public class ContributorController {

    private final ContributorBusiness contributorBusiness;


    public ContributorController(ContributorBusiness contributorBusiness) {
        this.contributorBusiness = contributorBusiness;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addContributor(@RequestBody CreateContributorDTO dto) {

        contributorBusiness.addContributor(dto.getEmail(), dto.getGroupNumber());
            return "Nouveau contributeur pour le groupe " + dto.getGroupNumber();
    } 

    
}
