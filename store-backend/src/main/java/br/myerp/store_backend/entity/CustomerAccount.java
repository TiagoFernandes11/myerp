package br.myerp.store_backend.entity;

import br.myerp.store_backend.constants.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idErp;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private Role role;
}
