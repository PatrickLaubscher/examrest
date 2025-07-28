package com.hb.cda.examrest.controller.dto;

import com.hb.cda.examrest.controller.dto.user.UserDTO;



public class LoginResponseDTO {

    private String token;
    private UserDTO user;

    
    public LoginResponseDTO(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
