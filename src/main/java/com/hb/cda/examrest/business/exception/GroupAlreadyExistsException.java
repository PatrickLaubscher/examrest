package com.hb.cda.examrest.business.exception;

public class GroupAlreadyExistsException extends BusinessException {

    public GroupAlreadyExistsException() {
        super("Ce nom de groupe est déjà utilisé.");
    }
    
}
