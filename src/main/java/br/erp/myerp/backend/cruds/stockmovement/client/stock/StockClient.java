package br.erp.myerp.backend.cruds.stockmovement.client.stock;

import br.erp.myerp.backend.cruds.product.entity.Product;
import br.erp.myerp.backend.cruds.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.cruds.stock.entity.Stock;

public interface StockClient {

    Stock getByProductId(Long ProductId);

    Stock getById(Long stockId);

    void update(StockUpdateDTO stockUpdateDTO);
}
