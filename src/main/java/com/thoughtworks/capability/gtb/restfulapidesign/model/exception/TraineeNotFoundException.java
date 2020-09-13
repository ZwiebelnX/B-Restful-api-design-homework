package com.thoughtworks.capability.gtb.restfulapidesign.model.exception;

import org.springframework.http.HttpStatus;

public class TraineeNotFoundException extends BusinessBasicException {

    public TraineeNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "未找到ID为<" + id + ">的学员");
    }
}
