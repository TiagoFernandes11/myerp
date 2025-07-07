package br.myerp.store_backend.customeraccount.dto.customeraccount;

import lombok.Data;

@Data
public class CustomerAccountResponseDTO {

    private Long id;
    private Long idErp;
    private String email;
    private String password;
}
