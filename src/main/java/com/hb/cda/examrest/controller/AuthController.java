package com.hb.cda.examrest.controller;

import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.controller.dto.LoginCredentialsDTO;
import com.hb.cda.examrest.controller.dto.LoginResponseDTO;
import com.hb.cda.examrest.security.AuthService;
import com.hb.cda.examrest.security.TokenPair;

import jakarta.validation.Valid;


@RestController
@RequestMapping("")
public class AuthController {

    private final AuthService authservice;


    public AuthController(AuthService authservice) {
        this.authservice = authservice;
    }

    public ResponseCookie generateCookie(String refreshToken) {

        return ResponseCookie.from("refresh-token", refreshToken)
        .httpOnly(true)
        .secure(false) // il faudra le mettre en sécurisé, pas tant qu'on est en dev car pas en HTTPS
        .sameSite(SameSiteCookies.NONE.toString())
        .path("/api/refresh-token")
        .build()
        ;
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginCredentialsDTO credentials) {
        LoginResponseDTO responseDTO = authservice.login(credentials);
        String refreshToken = authservice.generateRefreshToken(responseDTO.getUser().getId());
        ResponseCookie refreshCookie = generateCookie(refreshToken);
        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
            .body(responseDTO);
    }
    
    @PostMapping("/api/refresh-token")
    public ResponseEntity<String> refreshToken(@CookieValue(name="refresh-token") String token) {

        try {
            TokenPair tokens = authservice.validateRefreshToken(token);
            ResponseCookie refreshCookie = generateCookie(tokens.getRefreshToken());
            return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(tokens.getJwt());
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token");
        }
    
    }

    
}
