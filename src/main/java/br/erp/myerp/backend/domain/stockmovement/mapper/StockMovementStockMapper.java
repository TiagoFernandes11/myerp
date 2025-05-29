package br.erp.myerp.backend.domain.stockmovement.mapper;

import br.erp.myerp.backend.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.domain.stock.entity.Stock;
import br.erp.myerp.backend.domain.stockmovement.dto.stock.StockDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring", implementationName = "StockMapperForStockMovementImpl")
public interface StockMovementStockMapper {

    StockUpdateDTO toStockUpdateDTO(StockDTO stockDTO);
}
