package br.erp.myerp.domain.customer.customeraccount.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountCreateDTO {

    private Long clientId;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
}
