package com.spring.app.exception;

import com.spring.app.payload.ErrorResponse;
import com.spring.app.payload.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ValidationErrorResponse.SimpleFieldError> errors = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    ValidationErrorResponse.SimpleFieldError error = new ValidationErrorResponse.SimpleFieldError();
                    error.setField(fieldError.getField());
                    error.setDefaultMessage(fieldError.getDefaultMessage());
                    error.setRejectedValue(fieldError.getRejectedValue());
                    return error;
                })
                .collect(Collectors.toList());
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("fields validation error");
        response.setDetails(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlePhoneAlreadyExistsException(PhoneAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UnsupportedDtoClassException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedDtoClass(UnsupportedDtoClassException e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(false);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
