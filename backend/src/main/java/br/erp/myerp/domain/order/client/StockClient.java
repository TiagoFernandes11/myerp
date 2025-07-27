package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementCreateDTO;

import java.util.List;

public interface StockClient {

    public boolean checkStock(List<OrderItem> orderItems);
}
