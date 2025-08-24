package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementUpdateDTO;

public interface StockMovementClient {

    StockMovementResponseDTO get(Long id);

    StockMovementResponseDTO create(StockMovementCreateDTO stockMovementCreateDTO);

    StockMovementUpdateDTO update(StockMovementUpdateDTO stockMovement);
}
