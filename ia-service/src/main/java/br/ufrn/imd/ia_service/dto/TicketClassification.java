package br.ufrn.imd.ia_service.dto;

public record TicketClassification(
    TicketCategory category,
    Float trust
) { }
