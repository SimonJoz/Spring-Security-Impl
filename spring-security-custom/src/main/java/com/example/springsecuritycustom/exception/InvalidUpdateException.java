package com.example.springsecuritycustom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidUpdateException extends RuntimeException {

    public InvalidUpdateException(String msg) {
        super(msg);
    }
}
