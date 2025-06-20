package br.erp.myerp.domain.stock.dto.stockmovementitem;

import br.erp.myerp.domain.stock.entity.StockMovement;

public class StockMovementItemResponseDTO {

    private long id;

    private long productId;

    private int quantity;

    private StockMovement stockMovement;
}
