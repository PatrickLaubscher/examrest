package com.hb.cda.examrest.security;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.controller.dto.LoginCredentialsDTO;
import com.hb.cda.examrest.controller.dto.LoginResponseDTO;
import com.hb.cda.examrest.controller.dto.user.UserMapper;
import com.hb.cda.examrest.model.RefreshToken;
import com.hb.cda.examrest.model.User;
import com.hb.cda.examrest.repository.RefreshTokenRepository;
import com.hb.cda.examrest.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserMapper userMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;



    public AuthServiceImpl(JwtUtil jwtUtil, AuthenticationManager authManager,
            UserMapper userMapper,
            RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userMapper = userMapper;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }


    public LoginResponseDTO login(LoginCredentialsDTO credentials) {
    
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword())
        );

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof User)) {
            throw new RuntimeException("L'objet authentifié n'est pas de type User.");
        }

        User user = (User) principal;
        String token = jwtUtil.generateToken(user);
        UserDTO userDTO = userMapper.toUserDTO(user);

        return new LoginResponseDTO(token, userDTO);
    }


    public String generateRefreshToken(String userId) {

        RefreshToken refreshToken = new RefreshToken();
        User user = userRepository.findById(userId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le compte client n'existe pas")
        );
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(LocalDateTime.now().plus(30, ChronoUnit.DAYS));
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getId();
    }


    public TokenPair validateRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findById(token).orElseThrow();
        if(refreshToken.isExpired()) {
            throw new RuntimeException("Refresh token expired");
        }
        User user = refreshToken.getUser();
        refreshTokenRepository.delete(refreshToken);
        String newToken = generateRefreshToken(user.getId());
        String jwt = jwtUtil.generateToken(user);
        return new TokenPair(newToken, jwt); 
    }


    @Transactional
    @Scheduled(fixedDelay=24, timeUnit= TimeUnit.HOURS)
    void cleanExpiredToken(){
        refreshTokenRepository.deleteExpired();
    }
    
}
