package br.erp.myerp.backend.domain.stockmovement.mapper;

import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementCreateDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementUpdateDTO;
import br.erp.myerp.backend.domain.stockmovement.entity.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    StockMovementResponseDTO toStockMovementDTO(StockMovement stockMovement);

    StockMovement toStockMovement(StockMovementCreateDTO stockMovementCreateDTO);

    StockMovement toStockMovement(StockMovementResponseDTO stockMovementResponseDTO);

    StockMovement toStockMovement(StockMovementUpdateDTO stockMovementUpdateDTO);
}
