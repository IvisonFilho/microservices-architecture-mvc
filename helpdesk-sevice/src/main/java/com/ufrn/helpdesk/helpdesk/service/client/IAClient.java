package com.ufrn.helpdesk.helpdesk.service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.ufrn.helpdesk.helpdesk.dto.TicketClassificationDTO;
import com.ufrn.helpdesk.helpdesk.dto.TicketIARequestDTO;

@Component
public class IAClient {

    private final RestClient restClient;

    public IAClient(RestClient restClient){
        this.restClient = restClient;
    }

    public TicketClassificationDTO classifyTicket(String description){
        return restClient.post()
                         .uri("/classification/classify")
                         .body(new TicketIARequestDTO(description))
                         .retrieve()
                         .body(TicketClassificationDTO.class);
    }
    
}
