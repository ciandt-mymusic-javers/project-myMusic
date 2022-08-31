package com.ciandt.summit.bootcamp2022.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class MusicNotFoundException extends RuntimeException {

}
