package br.ufrn.imd.ia_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import br.ufrn.imd.ia_service.dto.TicketClassification;
import br.ufrn.imd.ia_service.dto.TicketRequest;
import br.ufrn.imd.ia_service.service.ChatService;


@RestController
@RequestMapping("/classification") 
public class ClassificationController {
    
    private final ChatService chatService;
    
    public ClassificationController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/classify")
    public ResponseEntity<TicketClassification> ticketClassification(@RequestBody TicketRequest ticketRequest) {
        
      
        if(ticketRequest.descriptionTicket() == null || ticketRequest.descriptionTicket().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        
     
        TicketClassification ticketClassification = chatService.classification(ticketRequest.descriptionTicket());

     
        return ResponseEntity.ok(ticketClassification);
    }
    
}