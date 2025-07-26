package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;

public interface StockMovementItemClient {

    StockMovementItemResponseDTO save(StockMovementItemCreateDTO si);
}
