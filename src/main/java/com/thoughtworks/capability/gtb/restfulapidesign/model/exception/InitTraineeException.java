package com.thoughtworks.capability.gtb.restfulapidesign.model.exception;

import org.springframework.http.HttpStatus;

public class InitTraineeException extends BusinessBasicException {

    public InitTraineeException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "初始化学员失败");
    }
}
