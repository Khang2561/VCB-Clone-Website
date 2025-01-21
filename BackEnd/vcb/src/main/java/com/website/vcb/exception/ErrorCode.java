package com.website.vcb.exception;

public enum ErrorCode {
    //Exception
    USER_EXISTED (1001, "User existed"),
    USER_NOT_EXISTED (1002, "User does not exist"),
    UNAUTHENTICATED (1003, "Unauthenticated"),
    //Validate
    INVALID_KEY (2000, "Invalid key"),
    USERNAME_INVALID (2001, "Must be at least 3 characters"),
    PASSWORD_INVALID (2002, "Password must be at least 8 characters"),
    //Other
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error");

    private int code;
    private String message;

    ErrorCode (int code, String msg) {
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
