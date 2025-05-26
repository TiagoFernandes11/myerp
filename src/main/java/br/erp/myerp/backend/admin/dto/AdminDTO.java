package br.erp.myerp.backend.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminDTO {

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password can not be blank")
    private String password;
}
