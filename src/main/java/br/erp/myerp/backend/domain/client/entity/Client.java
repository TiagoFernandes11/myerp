package br.erp.myerp.backend.domain.client.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String ddd;

    private String cellphone;

    private LocalDate birthday;

    @Column(unique = true, nullable = false)
    private String cpf;
}
