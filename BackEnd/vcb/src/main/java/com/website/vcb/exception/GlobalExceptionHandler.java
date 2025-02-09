package com.website.vcb.exception;

import com.website.vcb.dto.request.ApiResponese;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


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
        return ResponseEntity
                .status(errorCode.getCode())
                .body(apiResponese);
    }

    // Không có quyền truy cập
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponese> handleAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponese.builder()
                        .code(ErrorCode.UNAUTHORIZED.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

    // Xử lý nếu ngoại lệ xảy ra khi dữ liệu đầu vào vi phạm ràng buộc validation
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
