package com.ufrn.helpdesk.helpdesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.helpdesk.helpdesk.dto.CommentRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.CommentResponseDTO;
import com.ufrn.helpdesk.helpdesk.service.CommentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/{comment-id}")
    public ResponseEntity<CommentResponseDTO> getComment(@PathVariable("comment-id") Long commentId) {
        return ResponseEntity.ok(commentService.findByCommentId(commentId));
    }

    @GetMapping("/tickets/{ticket-id}")
    public ResponseEntity<List<CommentResponseDTO>> getComments(@PathVariable("ticket-id") Long ticketId) {
        return ResponseEntity.ok(commentService.findAllByTicketId(ticketId));
    }
    
    

    @PostMapping("/create/{ticket-id}")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable("ticket-id") Long ticketId,@Valid @RequestBody CommentRequestDTO commentRequestDTO){
        return ResponseEntity.status(201).body(commentService.createComment(ticketId,commentRequestDTO));
    }

    @PutMapping("/update/{comment-id}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable("comment-id") Long id, @Valid @RequestBody CommentRequestDTO commentRequestDTO) {        
        return ResponseEntity.ok(commentService.updateComment(id, commentRequestDTO));
    }

    @DeleteMapping("/delete/{comment-id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("comment-id") Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
