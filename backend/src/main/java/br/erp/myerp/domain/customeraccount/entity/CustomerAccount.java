package br.erp.myerp.domain.customeraccount.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
}
