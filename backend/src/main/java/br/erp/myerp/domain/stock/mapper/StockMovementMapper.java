package br.erp.myerp.domain.stock.mapper;

import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementCreateDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementResponseDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementUpdateDTO;
import br.erp.myerp.domain.stock.entity.StockMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {

    StockMovementResponseDTO toStockMovementDTO(StockMovement stockMovement);

    StockMovement toStockMovement(StockMovementCreateDTO stockMovementCreateDTO);

    StockMovement toStockMovement(StockMovementResponseDTO stockMovementResponseDTO);

    StockMovement toStockMovement(StockMovementUpdateDTO stockMovementUpdateDTO);
}
