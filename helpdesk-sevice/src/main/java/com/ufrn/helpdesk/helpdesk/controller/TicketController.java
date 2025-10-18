package com.ufrn.helpdesk.helpdesk.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.helpdesk.helpdesk.dto.TicketRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.TicketResponseDTO;
import com.ufrn.helpdesk.helpdesk.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    
    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO ticketRequestDTO){
        TicketResponseDTO ticketResponseDTO = ticketService.createTicket(ticketRequestDTO);
        return ResponseEntity.status(201).body(ticketResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@Valid @PathVariable Long id, @RequestBody TicketRequestDTO ticketRequestDTO){
        TicketResponseDTO ticketResponseDTO = ticketService.updateTicket(id, ticketRequestDTO);
        return ResponseEntity.ok(ticketResponseDTO);
    }

    
}
