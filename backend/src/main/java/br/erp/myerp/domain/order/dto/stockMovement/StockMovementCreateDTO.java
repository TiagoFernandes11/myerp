package br.erp.myerp.domain.order.dto.stockMovement;


import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.stock.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StockMovementCreateDTO {

    @NotEmpty(message = "Stock movement items can not be empty")
    private List<StockMovementItemCreateDTO> items;

    @NotNull(message = "Movement type can not be null")
    private MovementType type;

    @NotBlank(message = "Description can not be blank")
    private String description;
}
