package br.erp.myerp.domain.stock.dto.stockmovement;

import br.erp.myerp.domain.stock.entity.StockMovementItem;
import br.erp.myerp.domain.stock.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementResponseDTO {

    private Long id;
    private List<StockMovementItem> items;
    private MovementType type;
    private String description;
    private LocalDateTime timestamp;

}
