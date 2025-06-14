package br.erp.myerp.common.security.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {

    @NotNull(message = "Username can not be null")
    @NotBlank(message = "Username can not be blank")
    private String username;

    @NotNull(message = "Username can not be null")
    @NotBlank(message = "Username can not be blank")
    private String password;
}
