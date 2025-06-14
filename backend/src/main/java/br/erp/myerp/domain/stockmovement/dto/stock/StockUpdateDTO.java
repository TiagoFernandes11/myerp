package br.erp.myerp.domain.stockmovement.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockUpdateDTO {

    @NotNull(message = "Product id can not be null")
    private Long productId;

    @NotNull(message = "Quantity can not be null")
    private int quantity;
}
