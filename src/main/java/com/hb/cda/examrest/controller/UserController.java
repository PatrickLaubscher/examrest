package com.hb.cda.examrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.cda.examrest.business.AccountBusiness;
import com.hb.cda.examrest.controller.dto.user.CreateUserDTO;
import com.hb.cda.examrest.controller.dto.user.UserDTO;
import com.hb.cda.examrest.controller.dto.user.UserMapper;

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



    @GetMapping("")
    public UserDTO getUserByEmail(@RequestParam String email) {
        return userMapper.toDTO(accountBusiness.findUserByEmail(email));
    }
    


    @PostMapping("/create")
    public String create(@RequestBody @Valid CreateUserDTO dto) {
        accountBusiness.register(userMapper.toEntity(dto));
            return "Compte créé";
    } 

    
    
}
