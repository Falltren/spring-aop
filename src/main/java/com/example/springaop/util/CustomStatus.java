package com.example.springaop.util;

public enum CustomStatus {
    SUCCESS(0, "Success"),
    NOT_FOUND(1, "Not found"),
    EXCEPTION(2, "Exception");

    private final int code;
    private final String message;

    CustomStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
