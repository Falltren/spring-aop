package com.example.springaop.util;

import lombok.Data;

import java.util.Collection;

@Data
public class CustomResponse<T> {
    private int code;

    private String message;
    private Collection<T> responseList;

    public CustomResponse(Collection<T> responseList, CustomStatus status) {
        this.responseList = responseList;
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
