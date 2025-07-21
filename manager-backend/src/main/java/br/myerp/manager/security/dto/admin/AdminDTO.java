package br.myerp.manager.security.dto.admin;

import br.myerp.manager.security.constants.Role;
import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
