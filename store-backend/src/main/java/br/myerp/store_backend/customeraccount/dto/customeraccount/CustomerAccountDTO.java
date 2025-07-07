package br.myerp.store_backend.customeraccount.dto.customeraccount;

import lombok.Data;

@Data
public class CustomerAccountDTO {

    private Long id;
    private Long clientId;
    private String email;
    private String password;
}
