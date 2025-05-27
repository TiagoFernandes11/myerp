package br.erp.myerp.backend.cruds.stockmovement.mapper;

import br.erp.myerp.backend.cruds.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.backend.cruds.stockmovement.entity.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    StockMovementResponseDTO toStockMovementDTO(StockMovement stockMovement);

    StockMovement toStockMovement(StockMovementResponseDTO stockMovementResponseDTO);
}
