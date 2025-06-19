package br.erp.myerp.domain.stock.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class StockCreateDTO {

    @NotNull(message = "Product id can not be null")
    private Long productId;

    @NotNull(message = "Quantity can not be null")
    @PositiveOrZero(message = "Quantity must be greater or equal than 0")
    private int quantity;
}
