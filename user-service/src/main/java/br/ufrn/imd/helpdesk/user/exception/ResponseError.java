package br.ufrn.imd.helpdesk.user.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {
    private int status;
    private String message;
    private String description;
    private Date date;
}
