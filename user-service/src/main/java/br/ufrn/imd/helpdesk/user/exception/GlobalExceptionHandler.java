package br.ufrn.imd.helpdesk.user.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseError> handleResponseStatusException(ResponseStatusException ex){
        ResponseError error = new ResponseError();
        error.setStatus(ex.getStatusCode().value());
        error.setMessage(ex.getReason());
        error.setDescription("Erro no microsserviço de usuário");
        error.setDate(new Date());

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleAllErrors(Exception ex){
        ex.printStackTrace();
        ResponseError error = new ResponseError();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setDescription("Erro inesperado no microsserviço de usuário");
        error.setDate(new Date());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
