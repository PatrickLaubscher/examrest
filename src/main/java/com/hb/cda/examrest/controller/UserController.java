package com.hb.cda.examrest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.AccountBusiness;
import com.hb.cda.examrest.controller.dto.CreateUserDTO;
import com.hb.cda.examrest.controller.dto.UserMapper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final AccountBusiness accountBusiness;
    private final UserMapper userMapper;

    public UserController(AccountBusiness accountBusiness, UserMapper userMapper) {
        this.accountBusiness = accountBusiness;
        this.userMapper = userMapper;
    }


    @PostMapping("/create")
    public String create(@RequestBody @Valid CreateUserDTO dto) {
        
        accountBusiness.register(userMapper.toEntity(dto));
            return "Compte créé";
    } 

    
    
}
