package br.ufrn.imd.ia_service.service;

import br.ufrn.imd.ia_service.dto.TicketClassification;

public interface ChatService {
    TicketClassification classification(String descriptionTicket);
}

