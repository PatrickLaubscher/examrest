package com.hb.cda.examrest.security;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.controller.dto.LoginCredentialsDTO;
import com.hb.cda.examrest.controller.dto.LoginResponseDTO;


@Service
public interface AuthService {

    LoginResponseDTO login(LoginCredentialsDTO credentials);

    String generateRefreshToken(String idUser);

    TokenPair validateRefreshToken(String token);
    
}
