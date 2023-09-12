package com.example.springaop.util;

import java.util.Collection;

public class CustomResponse<T> {
    private int code;

    private String message;

    private Collection<T> responseList;
}
