package com.hb.cda.examrest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.ContributorBusiness;
import com.hb.cda.examrest.controller.dto.CreateContributorDTO;


@RestController
@RequestMapping("/api/contributor")
public class ContributorController {

    private final ContributorBusiness contributorBusiness;


    public ContributorController(ContributorBusiness contributorBusiness) {
        this.contributorBusiness = contributorBusiness;
    }

    @PostMapping("/add")
    public String addContributor(@RequestBody CreateContributorDTO dto) {

        contributorBusiness.addContributor(dto.getEmail(), dto.getGroupNumber());
            return "Nouveau contributeur pour le groupe " + dto.getGroupNumber();
    } 

    
}
