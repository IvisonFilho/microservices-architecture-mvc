package br.ufrn.imd.helpdesk.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufrn.imd.helpdesk.user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
   @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND u.active = true")
    Optional<UserEntity> findById(@Param("id") Long id);
    boolean existsByEmail(String email);
}
