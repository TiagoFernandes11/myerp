package br.erp.myerp.domain.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    private boolean removed;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String ddd;

    private String cellphone;

    private LocalDate birthday;

    @Column(unique = true, nullable = false)
    private String cpf;
}
