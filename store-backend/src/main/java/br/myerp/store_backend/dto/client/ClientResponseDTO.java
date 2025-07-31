package br.myerp.store_backend.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {

    private Long id;

    private boolean active;

    private boolean removed;

    private String firstName;

    private String lastName;

    private String email;

    private String ddd;

    private String cellphone;

    private LocalDate birthday;

    private String cpf;

}

