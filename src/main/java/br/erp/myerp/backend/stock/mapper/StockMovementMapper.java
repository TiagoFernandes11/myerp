package br.erp.myerp.backend.stock.mapper;

import br.erp.myerp.backend.stock.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.backend.stock.entity.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    StockMovementResponseDTO toStockMovementDTO(StockMovement stockMovement);

    StockMovement toStockMovement(StockMovementResponseDTO stockMovementResponseDTO);
}
