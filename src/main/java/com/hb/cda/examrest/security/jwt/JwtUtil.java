package com.hb.cda.examrest.security.jwt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hb.cda.examrest.security.UserService;


@Service
public class JwtUtil {

    private final UserService userService;
    private final KeyManager keyManager;

    
    public JwtUtil(UserService userService, KeyManager keyManager) {
        this.userService = userService;
        this.keyManager = keyManager;
    }

    /**
     * Génère un JWT contenant l'identifiant du user passé en paramètre
     * par défaut son temps d'expiration est de 30 minutes
     * @param user Le User pour lequel on souhaite créer un JWT
     * @return Le JWT généré
     */
    public String generateToken(UserDetails user) {
        return generateToken(user, Instant.now().plus(30, ChronoUnit.MINUTES));
    }

    /**
     * Génère un JWT contenant l'identifiant du user passé en paramètre
     * 
     * @param user Le User pour lequel on souhaite créer un JWT
     * @param expiration Le temps d'expiration du token
     * @return Le JWT généré
     */
    public String generateToken(UserDetails user, Instant expiration) {

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiration)
                .sign(keyManager.getAlgorithm());
    }


    public UserDetails validateToken(String token) {

        try{
            DecodedJWT decodedJWT = JWT
            .require(keyManager.getAlgorithm())
            .build()
            .verify(token);

            String userIdentifier = decodedJWT.getSubject();
            return userService.loadUserByUsername(userIdentifier);
            
        } catch(JWTVerificationException | UsernameNotFoundException e) {
            throw new AuthorizationDeniedException("Error verifying token");
        }

    }


    private Algorithm geAlgorithmFromKeys() {

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            File pubFile = new File("public.key");
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Files.readAllBytes(pubFile.toPath()));
            File privFile = new File("private.key");
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Files.readAllBytes(privFile.toPath()));

            return Algorithm.RSA256(
                (RSAPublicKey) keyFactory.generatePublic(pubKeySpec),
                (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec)
            );

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException | IOException e) {
            throw new RuntimeException("Error getting key pair for JWT", e);
        }
        
        
    }


}
