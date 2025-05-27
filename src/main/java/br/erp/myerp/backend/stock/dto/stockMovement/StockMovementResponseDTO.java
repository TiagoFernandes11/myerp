package br.erp.myerp.backend.stock.dto.stockMovement;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.entity.Stock;
import br.erp.myerp.backend.stock.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementResponseDTO {

    @NotNull(message = "ID can not be null")
    private Long id;

    @NotNull(message = "product can not be null")
    private Stock stock;

    @NotNull(message = "Movement type can not be null")
    private MovementType type;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotBlank(message = "Description can not be blank")
    private String description;

    private LocalDateTime timestamp;
}
