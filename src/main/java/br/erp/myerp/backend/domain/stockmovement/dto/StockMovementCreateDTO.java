package br.erp.myerp.backend.domain.stockmovement.dto;

import br.erp.myerp.backend.domain.stockmovement.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockMovementCreateDTO {

    @NotNull(message = "product can not be null")
    private Long stockId;

    @NotNull(message = "Movement type can not be null")
    private MovementType type;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotBlank(message = "Description can not be blank")
    private String description;
}
