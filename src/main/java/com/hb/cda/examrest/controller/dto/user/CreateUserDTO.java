package com.hb.cda.examrest.controller.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class CreateUserDTO {

    @NotBlank
    @Size(min=4, max=64)
    private String lastname;

    @NotBlank
    @Size(min=4, max=64)
    private String firstname;

    @NotBlank
    @Email
    @Size(min=4, max=64)
    private String email;

    @NotBlank
    @Size(min=4, max=64)
    private String password;
    

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

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
