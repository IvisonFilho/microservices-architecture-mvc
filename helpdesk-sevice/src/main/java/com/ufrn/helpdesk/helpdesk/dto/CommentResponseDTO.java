package com.ufrn.helpdesk.helpdesk.dto;

import java.time.LocalDateTime;

public record CommentResponseDTO(
    Long id,
    String author,
    String message,
    LocalDateTime dateTime
) {
    
}
