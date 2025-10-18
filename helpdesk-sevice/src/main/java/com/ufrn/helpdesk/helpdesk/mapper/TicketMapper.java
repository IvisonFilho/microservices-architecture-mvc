package com.ufrn.helpdesk.helpdesk.mapper;

import java.time.LocalDateTime;

import com.ufrn.helpdesk.helpdesk.dto.TicketResponseDTO;
import com.ufrn.helpdesk.helpdesk.dto.TicketRequestDTO;
import com.ufrn.helpdesk.helpdesk.model.Ticket;

public class TicketMapper {
    

    public static Ticket toEntity(TicketRequestDTO ticketResquestDTO){ 
        Ticket ticket = new Ticket();
        ticket.setUserId(ticketResquestDTO.userId());
        ticket.setTittle(ticketResquestDTO.tittle());
        ticket.setDescription(ticketResquestDTO.tittle());
        ticket.setVisibility(ticketResquestDTO.visibility());
        ticket.setStatus("Ativo");
        ticket.setDateTime(LocalDateTime.now());

        return ticket;
    }

    public static TicketResponseDTO toResponseDTO(Ticket ticket){
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO(ticket.getId(),ticket.getTittle(),ticket.getDescription(),ticket.getVisibility(),ticket.getStatus(),ticket.getDateTime());
    
        return ticketResponseDTO;
    }

    public static TicketRequestDTO toResquestDto(Ticket ticket){
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO(ticket.getUserId(),ticket.getTittle(),ticket.getDescription(),ticket.getVisibility());
    
        return ticketRequestDTO;
    }

}
