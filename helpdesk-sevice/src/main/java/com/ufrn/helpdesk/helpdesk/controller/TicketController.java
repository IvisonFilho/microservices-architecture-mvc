package com.ufrn.helpdesk.helpdesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/{ticket-id}")
    public ResponseEntity<TicketResponseDTO> getTicket(@PathVariable("ticket-id") Long ticketId) {
        return ResponseEntity.ok(ticketService.findByIdTicket(ticketId));
    }

    @GetMapping("/tickets/{user-id}")
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(ticketService.findAll(userId));
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO ticketRequestDTO){
        return ResponseEntity.status(201).body(ticketService.createTicket(ticketRequestDTO));
    }

    @PutMapping("/update/{ticket-id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable("ticket-id") Long ticketId, @Valid @RequestBody TicketRequestDTO ticketRequestDTO){
        return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticketRequestDTO));
    }
    
    @DeleteMapping("/delete/{ticket-id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("ticket-id") Long ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
