package com.e_commerce.demo.exception;

import com.e_commerce.demo.constants.ErrorMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {

        return buildErrorResponse(HttpStatus.NOT_FOUND,ex,request);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, HttpServletRequest request){
        return buildErrorResponse(HttpStatus.CONFLICT,ex,request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST,ex,request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request){
        return buildErrorResponse(HttpStatus.UNAUTHORIZED,ex,request);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.FORBIDDEN,ex,request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request){
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        errors.put(fieldError.getField(),fieldError.getDefaultMessage()));

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                .message(ErrorMessages.VALIDATION_FAILED)
                .path(request.getRequestURI())
                .validationErrors(errors)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    //always at the last
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>handleGlobalException(Exception exception, HttpServletRequest request){
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,exception,request);
    }


    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus httpStatus,Exception exception,HttpServletRequest request){
        return ResponseEntity.status(httpStatus)
                .body(ErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(httpStatus.value())
                                .error(httpStatus.getReasonPhrase())
                                .message(exception.getMessage())
                                .path(request.getRequestURI())
                                .build()

                );
    }

}
