package com.example.app.exceptions;

import com.example.app.dto.ErrorDetails;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Ошибки валидации (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException ex) {
        // Берем только первое сообщение об ошибке для компактности
        String errorMsg = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation Failed", errorMsg);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 2. Ресурс не найден (твой кастомный Exception)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // 3. Нарушение уникальности/констреинтов (база данных)
    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ErrorDetails> handleDatabaseErrors(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Database error: Duplicate entry or constraint violation",
                ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT); // 409 Conflict лучше подходит для дубликатов
    }


    /*
    // 4. Глобальный перехват всех остальных ошибок
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Server Error", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/
}