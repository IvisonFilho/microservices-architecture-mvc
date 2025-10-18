package com.ufrn.helpdesk.helpdesk.service;


import org.springframework.stereotype.Service;

import com.ufrn.helpdesk.helpdesk.repository.CommentRepository;

@Service
public class CommentService {
    
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    // public ResponseEntity<TicketResponseDTO> createTicket(){

    // }
    
    
}
