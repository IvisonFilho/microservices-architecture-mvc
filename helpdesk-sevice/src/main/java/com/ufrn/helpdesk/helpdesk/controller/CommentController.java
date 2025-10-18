package com.ufrn.helpdesk.helpdesk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.helpdesk.helpdesk.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
}
