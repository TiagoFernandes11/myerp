package br.erp.myerp.domain.admin.dto;

import br.erp.myerp.domain.admin.constants.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminDTO {

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email must be valid")
    private String username;

    @NotBlank(message = "Password can not be blank")
    private String password;

    private Role role;
}
