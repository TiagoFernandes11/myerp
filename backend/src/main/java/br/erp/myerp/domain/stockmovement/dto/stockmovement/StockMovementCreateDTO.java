package br.erp.myerp.domain.stockmovement.dto.stockmovement;

import br.erp.myerp.domain.stockmovement.entity.StockMovementItem;
import br.erp.myerp.domain.stockmovement.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class StockMovementCreateDTO {

    @NotEmpty(message = "Stock movement items can not be empty")
    private List<StockMovementItem> items;

    @NotNull(message = "Movement type can not be null")
    private MovementType type;

    @NotBlank(message = "Description can not be blank")
    private String description;
}
