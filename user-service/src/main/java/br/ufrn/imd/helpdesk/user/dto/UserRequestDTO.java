package br.ufrn.imd.helpdesk.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    @NotBlank(message = "Name is obligatory")
    String name,
    @Email(message = "Email is obligatory") 
    String email,
    @NotBlank(message = "Password is obligatory") 
    String password,
    String address
    ) {
}
