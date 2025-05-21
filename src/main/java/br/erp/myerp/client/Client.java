package br.erp.myerp.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @CPF(message = "Your cpf must be valid")
    private String cpf;


}
