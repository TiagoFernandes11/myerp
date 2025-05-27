package br.erp.myerp.backend.cruds.stockmovement.dto;

import br.erp.myerp.backend.cruds.stock.entity.Stock;
import br.erp.myerp.backend.cruds.stockmovement.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockMovementCreateDTO {

    @NotNull(message = "product can not be null")
    private Stock stock;

    @NotNull(message = "Movement type can not be null")
    private MovementType type;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotBlank(message = "Description can not be blank")
    private String description;
}
