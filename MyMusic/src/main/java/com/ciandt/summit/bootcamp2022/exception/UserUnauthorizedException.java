package com.ciandt.summit.bootcamp2022.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException{

    public UserUnauthorizedException(String message){
        super(message);
    }
}
