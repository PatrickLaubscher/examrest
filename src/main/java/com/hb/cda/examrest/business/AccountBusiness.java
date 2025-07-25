package com.hb.cda.examrest.business;

import org.springframework.stereotype.Service;

import com.hb.cda.examrest.model.User;


@Service
public interface AccountBusiness {
    User register(User user);
    User findUserByEmail(String email);
    void resetPassword(String email);
    void deleteAccount(User user); 
}
