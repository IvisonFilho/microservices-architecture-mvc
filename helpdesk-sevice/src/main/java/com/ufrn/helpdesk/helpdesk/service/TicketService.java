package com.ufrn.helpdesk.helpdesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufrn.helpdesk.helpdesk.dto.TicketRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.TicketResponseDTO;
import com.ufrn.helpdesk.helpdesk.exception.ResourceNotFoundException;
import com.ufrn.helpdesk.helpdesk.mapper.TicketMapper;
import com.ufrn.helpdesk.helpdesk.model.Ticket;
import com.ufrn.helpdesk.helpdesk.repository.TicketRepository;

@Service
public class TicketService {
    
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<TicketResponseDTO> findAll(Long userId){
        if (userId == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo");
        }
        
        List<Ticket> tickets =  ticketRepository.findAllByUserId(userId);

        return tickets.stream()
                      .map(TicketMapper::toResponseDTO)
                      .toList();
    }

    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO){
        Ticket ticket = TicketMapper.toEntity(ticketRequestDTO);
        ticketRepository.save(ticket);

        return TicketMapper.toResponseDTO(ticket);
    }

    public TicketResponseDTO updateTicket(Long id, TicketRequestDTO ticketRequestDTO){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        ticket.setUserId(ticketRequestDTO.userId());
        ticket.setTittle(ticketRequestDTO.tittle());
        ticket.setDescription(ticketRequestDTO.description());
        ticket.setVisibility(ticketRequestDTO.visibility());

        Ticket ticketSave = ticketRepository.save(ticket);

        return TicketMapper.toResponseDTO(ticketSave);
    }
}
