package com.wandted.matitnyam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UnauthorizedException extends SecurityException {

    public UnauthorizedException(final String message) {
        super(message);
    }
}
