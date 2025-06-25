package br.erp.myerp.domain.customeraccount.dto;

import lombok.Data;

@Data
public class CustomerAccountDTO {

    private Long id;
    private Long clientId;
    private String email;
    private String password;
}
