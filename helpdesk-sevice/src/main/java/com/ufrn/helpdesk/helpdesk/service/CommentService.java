package com.ufrn.helpdesk.helpdesk.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ufrn.helpdesk.helpdesk.dto.CommentRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.CommentResponseDTO;
import com.ufrn.helpdesk.helpdesk.exception.ResourceNotFoundException;
import com.ufrn.helpdesk.helpdesk.mapper.CommentMapper;
import com.ufrn.helpdesk.helpdesk.model.Comment;
import com.ufrn.helpdesk.helpdesk.model.Ticket;
import com.ufrn.helpdesk.helpdesk.repository.CommentRepository;
import com.ufrn.helpdesk.helpdesk.repository.TicketRepository;

@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;

    public CommentService(CommentRepository commentRepository, TicketRepository ticketRepository){
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
    }

    public CommentResponseDTO findByCommentId(Long commentId){
        if(commentId == null){
            throw new IllegalArgumentException("Comment id cannot null");
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment is not found"));
    
        return CommentMapper.toResponseDto(comment);
    }

    public List<CommentResponseDTO> findAllByTicketId(Long ticketId){
        if(ticketId == null){
            throw new IllegalArgumentException("Comment id cannot null");
        }
        List<Comment> comments = commentRepository.findByTicketId(ticketId);

        return comments.stream()
                      .map(CommentMapper::toResponseDto)
                      .collect(Collectors.toList());
    }
    

    public CommentResponseDTO createComment(Long ticketId, CommentRequestDTO commentRequestDTO){
        Comment comment = CommentMapper.toEntity(commentRequestDTO);
        
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException("Ticket is not found"));

        comment.setTicket(ticket);
        comment.setDateTime(LocalDateTime.now());
        comment.setActivated(true);
        Comment commentSave = commentRepository.save(comment);        

        return CommentMapper.toResponseDto(commentSave);
    }
    
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO ){
        if(commentId == null){
            throw new IllegalArgumentException("Comment id cannot null");
        }
        
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment is not found"));

        comment.setMessage(commentRequestDTO.message());

        Comment commentUpdate = commentRepository.save(comment);

        return CommentMapper.toResponseDto(commentUpdate);
    }

    public void deleteComment(Long commentId){
        if (commentId == null) {
             throw new IllegalArgumentException("Ticket id cannot be null");
        }

        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment is not found"));

        if(!comment.isActivated()){
            throw new ResourceNotFoundException("Comment is not activates");
        }

        comment.setActivated(false);
        commentRepository.save(comment);
    }



}
