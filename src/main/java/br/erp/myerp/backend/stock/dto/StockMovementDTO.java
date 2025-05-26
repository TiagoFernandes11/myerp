package br.erp.myerp.backend.stock.dto;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockMovementDTO {

    @NotNull(message = "product can not be null")
    private Product product;

    @NotNull(message = "Movement type can not be null")
    private MovementType type;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotBlank(message = "Description can not be blank")
    private String description;

    private LocalDateTime timestamp;
}
