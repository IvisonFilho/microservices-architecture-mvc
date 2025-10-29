package com.ufrn.helpdesk.helpdesk.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.ufrn.helpdesk.helpdesk.dto.CommentRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.CommentResponseDTO;
import com.ufrn.helpdesk.helpdesk.service.CommentService;

import jakarta.validation.Valid;

@Controller
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    // Queries
    @QueryMapping
    public CommentResponseDTO getComment(@Argument Long id) {
        return commentService.findByCommentId(id);
    }

    @QueryMapping
    public List<CommentResponseDTO> getComments(@Argument Long ticketId) {
        return commentService.findAllByTicketId(ticketId);
    }

    // Mutations
    @MutationMapping
    public CommentResponseDTO createComment(
        @Argument Long ticketId,
        @Argument("comment") @Valid CommentRequestDTO commentRequestDTO
    ) {
        return commentService.createComment(ticketId, commentRequestDTO);
    }

    @MutationMapping
    public CommentResponseDTO updateComment(
        @Argument Long id,
        @Argument("comment") @Valid CommentRequestDTO commentRequestDTO
    ) {
        return commentService.updateComment(id, commentRequestDTO);
    }

    @MutationMapping
    public Boolean deleteComment(@Argument Long id) {
        commentService.deleteComment(id);
        return true;
    }
}
