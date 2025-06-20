package br.erp.myerp.domain.stock.mapper;

import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemUpdateDTO;
import br.erp.myerp.domain.stock.entity.StockMovementItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementItemMapper {

    StockMovementItem toStockMovementItem(StockMovementItemCreateDTO stockMovementItemCreateDTO);

    StockMovementItem toStockMovementItem(StockMovementItemUpdateDTO stockMovementItemUpdateDTO);

    StockMovementItemResponseDTO toStockMovementItemResponseDTO(StockMovementItem stockMovementItem);
}
