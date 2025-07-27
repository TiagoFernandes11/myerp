package br.erp.myerp.domain.stock.dto.stockmovementitem;

import br.erp.myerp.domain.stock.entity.StockMovement;
import lombok.Data;

@Data
public class StockMovementItemResponseDTO {

    private long id;

    private long productId;

    private int quantity;
}
