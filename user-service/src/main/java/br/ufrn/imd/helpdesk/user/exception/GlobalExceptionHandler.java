package br.ufrn.imd.helpdesk.user.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public String handleAllErros(Exception ex){
        ex.printStackTrace();
        return "erro";
    }

}
