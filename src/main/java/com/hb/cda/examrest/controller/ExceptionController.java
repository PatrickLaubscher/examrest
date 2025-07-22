package com.hb.cda.examrest.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hb.cda.examrest.business.exception.BusinessException;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail userExists(BusinessException e){

        switch(e.getClass().getSimpleName()) {
        case "GroupAlreadyExistsException":
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Le nom du groupe est déjà utilisé");
        case "UserAlreadyExistsException":
            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Le compte est déjà créé");
        default:
            return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown server problem");
        }

    };

}
