package com.ufrn.helpdesk.helpdesk.dto;

import java.time.LocalDateTime;

public record TicketResponseDTO(
    Long id,
    String title,
    String description,
    String visibility,
    String status,
    String categoryIA,
    LocalDateTime dateTime
) {
    
}
