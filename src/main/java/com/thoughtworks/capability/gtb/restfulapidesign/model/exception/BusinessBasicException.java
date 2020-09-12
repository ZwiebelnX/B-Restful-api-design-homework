package com.thoughtworks.capability.gtb.restfulapidesign.model.exception;

import org.springframework.http.HttpStatus;

public class BusinessBasicException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BusinessBasicException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
