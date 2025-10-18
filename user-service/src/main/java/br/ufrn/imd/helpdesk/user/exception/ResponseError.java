package br.ufrn.imd.helpdesk.user.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {
    
    int status;
    String message;
    String description;
    Date date;

}
