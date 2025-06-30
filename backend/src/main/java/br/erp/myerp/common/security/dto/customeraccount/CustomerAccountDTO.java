package br.erp.myerp.common.security.dto.customeraccount;

import br.erp.myerp.domain.admin.constants.Role;
import lombok.Data;

@Data
public class CustomerAccountDTO {

    private Long id;
    private Long clientId;
    private String email;
    private String password;
    private Role role;
}
