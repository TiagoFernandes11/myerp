package br.erp.myerp.domain.stock.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {

    private Long id;

    @NotNull(message = "Product id can not be null")
    private Long productId;

    @NotNull(message = "Quantity can not be null")
    @PositiveOrZero(message = "Quantity must be greater or equal than 0")
    private int quantity;

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}
