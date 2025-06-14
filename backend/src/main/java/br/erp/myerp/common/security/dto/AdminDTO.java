package br.erp.myerp.common.security.dto;

import br.erp.myerp.domain.admin.constants.Role;
import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
