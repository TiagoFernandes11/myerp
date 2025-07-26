package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;

public interface StockMovementClient {

    StockMovementResponseDTO create(StockMovementCreateDTO stockMovementCreateDTO);

    void update(Long id, StockMovementResponseDTO stockMovement);
}
