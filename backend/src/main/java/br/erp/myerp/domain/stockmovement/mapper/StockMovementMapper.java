package br.erp.myerp.domain.stockmovement.mapper;

import br.erp.myerp.domain.stockmovement.dto.stockmovement.StockMovementCreateDTO;
import br.erp.myerp.domain.stockmovement.dto.stockmovement.StockMovementResponseDTO;
import br.erp.myerp.domain.stockmovement.dto.stockmovement.StockMovementUpdateDTO;
import br.erp.myerp.domain.stockmovement.entity.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    StockMovementResponseDTO toStockMovementDTO(StockMovement stockMovement);

    StockMovement toStockMovement(StockMovementCreateDTO stockMovementCreateDTO);

    StockMovement toStockMovement(StockMovementResponseDTO stockMovementResponseDTO);

    StockMovement toStockMovement(StockMovementUpdateDTO stockMovementUpdateDTO);
}
