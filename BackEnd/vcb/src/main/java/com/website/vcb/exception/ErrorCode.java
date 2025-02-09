package com.website.vcb.exception;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    //------------------------------------------------------------------------------------------------
    //Exception
    USER_EXISTED (1001, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED (1002, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED (1003, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED (1004, "You do not have permission", HttpStatus.FORBIDDEN),
    //Validate
    INVALID_KEY (2000, "Invalid key", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID (2001, "Must be at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID (2002, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    //Other
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR);
    //------------------------------------------------------------------------------------------------

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode (int code, String msg, HttpStatusCode statusCode) {
        this.code = code;
        this.message = msg;
        this.statusCode = statusCode;
    }


}
