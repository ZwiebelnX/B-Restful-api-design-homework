package com.thoughtworks.capability.gtb.restfulapidesign.model.exception;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends BusinessBasicException {

    public TeamNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "未找到ID为<" + id + ">的分组");
    }
}
