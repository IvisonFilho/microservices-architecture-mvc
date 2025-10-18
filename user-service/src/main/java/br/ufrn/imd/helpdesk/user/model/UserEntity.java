package br.ufrn.imd.helpdesk.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor //JPA exige
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor gerado automaticamente pelo banco
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String address;

    @Column(nullable = false)
    private boolean active = true;

}
