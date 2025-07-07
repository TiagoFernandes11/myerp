package br.myerp.store_backend.customeraccount.dto.customeraccount;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountCreateDTO {

    private Long idErp;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
}
