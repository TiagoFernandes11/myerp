package br.erp.myerp.domain.stock.dto.stockmovementitem;

import br.erp.myerp.domain.stock.entity.StockMovement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
