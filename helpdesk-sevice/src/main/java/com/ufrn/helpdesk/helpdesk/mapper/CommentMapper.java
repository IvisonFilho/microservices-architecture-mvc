package com.ufrn.helpdesk.helpdesk.mapper;

import com.ufrn.helpdesk.helpdesk.dto.CommentRequestDTO;
import com.ufrn.helpdesk.helpdesk.dto.CommentResponseDTO;
import com.ufrn.helpdesk.helpdesk.model.Comment;

public class CommentMapper {
    
    public static Comment toEntity(CommentRequestDTO commentRequestDTO){
        Comment comment = new Comment();
        comment.setAuthor(commentRequestDTO.author());
        comment.setMessage(commentRequestDTO.message());
        
        return comment;
    }

    public static CommentResponseDTO toResponseDto(Comment comment){
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment.getAuthor(),comment.getMessage(),comment.getDateTime());
        
        return commentResponseDTO;
    }

    public static CommentRequestDTO toRequestDto(Comment comment){
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO(comment.getAuthor(), comment.getMessage());

        return commentRequestDTO;
    }
}
