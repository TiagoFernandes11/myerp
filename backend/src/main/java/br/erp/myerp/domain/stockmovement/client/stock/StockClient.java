package br.erp.myerp.domain.stockmovement.client.stock;

import br.erp.myerp.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.domain.stockmovement.dto.stock.StockDTO;

public interface StockClient {

    StockDTO getByProductId(Long ProductId);

    StockDTO getById(Long stockId);

    void update(StockUpdateDTO stockUpdateDTO);
}
