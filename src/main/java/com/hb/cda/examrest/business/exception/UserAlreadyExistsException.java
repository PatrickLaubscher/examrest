package com.hb.cda.examrest.business.exception;


public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException() {
        super("L'utilisateur existe déjà.");
    }
    
}
