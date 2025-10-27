package com.ufrn.helpdesk.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequestDTO(
    @NotNull(message = "User ID is mandatory")
    Long userId,
    @NotBlank(message = "Tittle is mandatory")
    String title,
    @NotBlank(message = "Description is mandatory")
    String description,
    @NotBlank(message = "Visibility is mandatory")
    String visibility
) {
    
}
