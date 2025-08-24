package br.erp.myerp.domain.order.mapper;

import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemUpdateDTO;
import br.erp.myerp.domain.stock.entity.StockMovementItem;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StockMovementItemMapperForOrder {

    StockMovementItem toStockMovementItem(StockMovementItemCreateDTO stockMovementItemCreateDTO);

    StockMovementItem toStockMovementItem(StockMovementItemUpdateDTO stockMovementItemUpdateDTO);

    StockMovementItemResponseDTO toStockMovementItemResponseDTO(StockMovementItem stockMovementItem);

    StockMovementItem toStockMovementItem(StockMovementItemResponseDTO existingItem);
}
