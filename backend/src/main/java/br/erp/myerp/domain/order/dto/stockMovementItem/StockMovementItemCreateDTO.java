package br.erp.myerp.domain.order.dto.stockMovementItem;

import br.erp.myerp.domain.stock.entity.StockMovement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StockMovementItemCreateDTO {

    @NotBlank(message = "productId cannot be blank")
    @NotNull(message = "productId cannot be null")
    private long productId;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    @NotNull(message = "StockMovement cannot be null")
    private long stockMovementId;

}
