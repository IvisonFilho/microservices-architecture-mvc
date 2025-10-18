package com.ufrn.helpdesk.helpdesk.dto;

import java.time.LocalDateTime;

public record CommentResponseDTO(
    String author,
    String message,
    LocalDateTime dateTime
) {
    
}
