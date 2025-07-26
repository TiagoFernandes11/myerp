package br.erp.myerp.domain.order.dto.stockMovement;

import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.order.enums.MovementType;
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
    private List<StockMovementItemResponseDTO> items;
    private MovementType type;
    private String description;
    private LocalDateTime timestamp;

}
