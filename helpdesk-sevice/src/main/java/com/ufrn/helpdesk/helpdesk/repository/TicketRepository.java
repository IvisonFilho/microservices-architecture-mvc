package com.ufrn.helpdesk.helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrn.helpdesk.helpdesk.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{
    List<Ticket> findAllByUserId(Long userId);
    List<Ticket> findAllByUserIdAndActiveTrue(Long userId);
}
