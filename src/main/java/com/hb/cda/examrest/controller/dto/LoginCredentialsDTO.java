package com.hb.cda.examrest.controller.dto;

import jakarta.validation.constraints.NotBlank;


public class LoginCredentialsDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    

}
