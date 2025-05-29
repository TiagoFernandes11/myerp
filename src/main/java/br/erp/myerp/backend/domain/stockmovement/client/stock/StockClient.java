package br.erp.myerp.backend.domain.stockmovement.client.stock;

import br.erp.myerp.backend.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.domain.stock.entity.Stock;

public interface StockClient {

    Stock getByProductId(Long ProductId);

    Stock getById(Long stockId);

    void update(StockUpdateDTO stockUpdateDTO);
}
