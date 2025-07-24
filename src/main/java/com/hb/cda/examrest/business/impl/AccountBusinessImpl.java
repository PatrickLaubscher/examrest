package com.hb.cda.examrest.business.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hb.cda.examrest.business.AccountBusiness;
import com.hb.cda.examrest.business.exception.UserAlreadyExistsException;
import com.hb.cda.examrest.model.User;
import com.hb.cda.examrest.repository.UserRepository;


@Service
public class AccountBusinessImpl implements AccountBusiness {

    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public AccountBusinessImpl(PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public User register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

       String rawPwd = user.getPassword();
       user.setPassword(passwordEncoder.encode(rawPwd));
       user.setRole("ROLE_USER");

       User savedUser = userRepository.save(user);

       return savedUser;

    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le compte user n'existe pas")
        );

    }

    @Override
    public void resetPassword(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetPassword'");
    }

    @Override
    public void deleteAccount(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }
    
}
