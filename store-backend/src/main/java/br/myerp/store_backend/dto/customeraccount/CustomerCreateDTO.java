package br.myerp.store_backend.dto.customeraccount;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerCreateDTO {

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotNull(message = "First name can not be null")
    @NotEmpty(message = "First name can not be empty")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @NotEmpty(message = "Last name can not be empty")
    private String lastName;

    @NotNull(message = "ddd can not be null")
    @NotEmpty(message = "ddd can not be empty")
    @Pattern(regexp = "^[0-9]{2}$")
    private String ddd;

    @NotNull(message = "Cellphone can not be null")
    @NotEmpty(message = "Cellphone can not be empty")
    private String cellphone;

    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

    //    @CPF(message = "CPF must be valid")
    @NotNull(message = "CPF can not be null")
    @NotEmpty(message = "CPF can not be empty")
    private String cpf;
}
