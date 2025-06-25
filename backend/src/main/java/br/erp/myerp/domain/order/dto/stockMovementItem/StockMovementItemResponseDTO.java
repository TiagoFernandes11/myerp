package br.erp.myerp.domain.order.dto.stockMovementItem;

import br.erp.myerp.domain.stock.entity.StockMovement;
import lombok.Data;

@Data
public class StockMovementItemResponseDTO {

    private long id;

    private long productId;

    private int quantity;

    private StockMovement stockMovement;
}
