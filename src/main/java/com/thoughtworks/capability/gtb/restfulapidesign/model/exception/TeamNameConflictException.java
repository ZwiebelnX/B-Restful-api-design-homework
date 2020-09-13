package com.thoughtworks.capability.gtb.restfulapidesign.model.exception;

import org.springframework.http.HttpStatus;

public class TeamNameConflictException extends BusinessBasicException {

    public TeamNameConflictException(String teamName) {
        super(HttpStatus.CONFLICT, "队名重复：" + teamName);
    }
}
