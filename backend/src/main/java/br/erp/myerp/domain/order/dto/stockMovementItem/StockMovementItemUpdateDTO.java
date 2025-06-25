package br.erp.myerp.domain.order.dto.stockMovementItem;

import br.erp.myerp.domain.stock.entity.StockMovement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockMovementItemUpdateDTO {

    @NotNull(message = "id cannot be null")
    private long id;

    @NotNull(message = "productId cannot be null")
    private long productId;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @NotNull(message = "StockMovement cannot be null")
    private StockMovement stockMovement;
}
