package com.ufrn.helpdesk.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(
    @NotBlank(message = "Author is mandatory")
    String author,
    @NotBlank(message = "Message is mandatory")
    String message
) {
    
}
