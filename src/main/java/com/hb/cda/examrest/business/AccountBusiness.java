package com.hb.cda.examrest.business;

import com.hb.cda.examrest.model.User;



public interface AccountBusiness {
    User register(User user);
    User findUserByEmail(String email);
    void resetPassword(String email);
    void deleteAccount(User user); 
}
