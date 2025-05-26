package br.erp.myerp.backend.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateDTO {

    @NotBlank(message = "First name can not be blank")
    private String firstName;

    @NotBlank(message = "Last name can not be blank")
    private String lastName;

    @Email(message = "Your email must be a valid one")
    private String email;

    @NotBlank(message = "DDD can not be null")
    @Pattern(regexp = "[0-9]{2}", message = "DDD must have 2 digits")
    private String ddd;

    @NotBlank(message = "Cellphone can not be null")
    @Pattern(regexp = "[0-9]{9}", message = "Cellphone must have 9 digits")
    private String cellphone;

    @Past(message = "Your birthday must be in the past")
    private LocalDate birthday;

    @NotBlank(message = "CPF can not be blank")
    @Pattern(regexp = "[0-9]{11}", message = "CPF must be 11 digits long")
    private String cpf;
}
