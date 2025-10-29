package com.ufrn.helpdesk.helpdesk.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ufrn.helpdesk.helpdesk.dto.TicketRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.TicketResponseDTO;
import com.ufrn.helpdesk.helpdesk.service.TicketService;

import jakarta.validation.Valid;

@Controller
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Queries
    @QueryMapping
    public TicketResponseDTO getTicket(@Argument Long id) {
        return ticketService.findByIdTicket(id);
    }

    @QueryMapping
    public List<TicketResponseDTO> getAllTickets(@Argument Long userId) {
        return ticketService.findAll(userId);
    }

    // Mutations
    @MutationMapping
    public TicketResponseDTO createTicket(@Argument("ticket") @Valid TicketRequestDTO ticketRequestDTO) {
        return ticketService.createTicket(ticketRequestDTO);
    }

    @MutationMapping
    public TicketResponseDTO updateTicket(@Argument Long id, @Argument("ticket") @Valid TicketRequestDTO ticketRequestDTO) {
        return ticketService.updateTicket(id, ticketRequestDTO);
    }

    @MutationMapping
    public Boolean deleteTicket(@Argument Long id) {
        ticketService.deleteTicket(id);
        return true;
    }
}
