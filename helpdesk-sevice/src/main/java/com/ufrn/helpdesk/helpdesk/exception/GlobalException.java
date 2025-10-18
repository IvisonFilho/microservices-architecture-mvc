package com.ufrn.helpdesk.helpdesk.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    // Validação de DTOs
    //Exception lançada caso validação do DTO não seja garantida
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validationException(MethodArgumentNotValidException exception){
        var errors = exception.getBindingResult()  //Spring guarda todos os erros dentro de BindingResult - erros de validacao
            .getFieldErrors() // Campos especificos
            .stream()                      
            .map(error -> Map.of("field", error.getField(), "message", error.getDefaultMessage()))//Field - Nome do campo no DTO. Message - Messagem anotada no DTO.
            .toList();

        return ResponseEntity.badRequest().body(Map.of(
            "status", 400,
            "error", "Validation failed",
            "errors", errors,
            "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> resourceNotFound(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
            "status",404,
            "error","Resource Not Found",
            "message", exception.getMessage(),
            "timestamp", LocalDateTime.now().toString()
        ));
    }

}
