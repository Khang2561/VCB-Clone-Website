package com.website.vcb.exception;

import com.website.vcb.dto.request.ApiResponese;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Hàm trả về nếu có lỗi nằm ngoài danh sách
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponese> handleRuntimeException(RuntimeException e) {
        ApiResponese apiResponese = new ApiResponese();
        apiResponese.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponese.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponese);
    }

    // Lỗi nằm trong danh sách
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponese> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponese apiResponese = new ApiResponese();
        apiResponese.setCode(errorCode.getCode());
        apiResponese.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponese);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponese> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try{
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException ex){

        }
        ApiResponese apiResponese = new ApiResponese();
        apiResponese.setCode(errorCode.getCode());
        apiResponese.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponese);
    }
}
