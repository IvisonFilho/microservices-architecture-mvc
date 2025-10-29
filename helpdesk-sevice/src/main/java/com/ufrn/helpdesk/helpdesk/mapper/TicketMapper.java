package com.ufrn.helpdesk.helpdesk.mapper;

import com.ufrn.helpdesk.helpdesk.dto.TicketResponseDTO;
import com.ufrn.helpdesk.helpdesk.dto.TicketRequestDTO;
import com.ufrn.helpdesk.helpdesk.model.Ticket;

public class TicketMapper {
    

    public static Ticket toEntity(TicketRequestDTO ticketResquestDTO){ 
        Ticket ticket = new Ticket();
        ticket.setUserId(ticketResquestDTO.userId());
        ticket.setTitle(ticketResquestDTO.title());
        ticket.setDescription(ticketResquestDTO.description());
        ticket.setVisibility(ticketResquestDTO.visibility());
        return ticket;
    }

    public static TicketResponseDTO toResponseDTO(Ticket ticket){
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO(ticket.getId(),ticket.getTitle(),ticket.getDescription(),ticket.getVisibility(),ticket.getStatus(),ticket.getCategoryIA(),ticket.getDateTime());
    
        return ticketResponseDTO;
    }

    public static TicketRequestDTO toResquestDto(Ticket ticket){
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO(ticket.getUserId(),ticket.getTitle(),ticket.getDescription(),ticket.getVisibility());
    
        return ticketRequestDTO;
    }

}
