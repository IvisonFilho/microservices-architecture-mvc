package com.ufrn.helpdesk.helpdesk.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    private String priority; //Microsserviço de IA
    
    private String categoryIA; //Microsserviço de IA
    
    @Column(nullable = false)
    private String visibility;
    
    @Column(nullable = false)
    private String status;

    private boolean active;
    
    @Column(nullable = false)
    private LocalDateTime dateTime;
    
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();
}
