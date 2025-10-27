package com.ufrn.helpdesk.helpdesk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponseDTO findByIdTicket(Long idTicket) {
        if (idTicket == null) {
            throw new IllegalArgumentException("Ticket id cannot be null");
        }

        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        return TicketMapper.toResponseDTO(ticket);
    }

    public List<TicketResponseDTO> findAll(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        List<Ticket> tickets = ticketRepository.findAllByUserId(userId);

        return tickets.stream()
                .map(TicketMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TicketResponseDTO> findAllActiveTrue(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        List<Ticket> tickets = ticketRepository.findAllByUserIdAndActiveTrue(userId);

        return tickets.stream()
                .map(TicketMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = TicketMapper.toEntity(ticketRequestDTO);
        ticket.setStatus("Ativo");
        ticket.setActive(true);
        ticket.setDateTime(LocalDateTime.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        return TicketMapper.toResponseDTO(savedTicket);
    }

    public TicketResponseDTO updateTicket(Long id, TicketRequestDTO ticketRequestDTO) {

        if (id == null) {
            throw new IllegalArgumentException("Ticket id cannot be null");
        }

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        ticket.setTitle(ticketRequestDTO.title());
        ticket.setDescription(ticketRequestDTO.description());
        ticket.setVisibility(ticketRequestDTO.visibility());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return TicketMapper.toResponseDTO(updatedTicket);
    }

    public void deleteTicket(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Ticket id cannot be null");
        }

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        if (!ticket.isActive()) {
            throw new ResourceNotFoundException("Ticket is already inactive");
        }
        
        ticket.setActive(false);
        ticketRepository.save(ticket);
    }

}
