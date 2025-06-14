package br.erp.myerp.domain.stockmovement.mapper;

import br.erp.myerp.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.domain.stock.entity.Stock;
import br.erp.myerp.domain.stockmovement.dto.stock.StockDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring", implementationName = "StockMapperForStockMovementImpl")
public interface StockMovementStockMapper {

    StockUpdateDTO toStockUpdateDTO(StockDTO stockDTO);
}
