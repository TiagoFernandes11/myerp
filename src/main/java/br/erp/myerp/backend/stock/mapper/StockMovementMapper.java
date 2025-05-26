package br.erp.myerp.backend.stock.mapper;

import br.erp.myerp.backend.stock.dto.StockMovementDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import br.erp.myerp.backend.stock.entity.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    StockMovementDTO toStockMovementDTO(StockMovement stockMovement);

    StockMovement toStockMovement(StockMovementDTO stockMovementDTO);
}
