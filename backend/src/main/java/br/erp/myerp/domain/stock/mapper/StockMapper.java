package br.erp.myerp.domain.stock.mapper;

import br.erp.myerp.domain.stock.dto.StockCreateDTO;
import br.erp.myerp.domain.stock.dto.StockResponseDTO;
import br.erp.myerp.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.domain.stock.entity.Stock;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockResponseDTO toStockDTO(Stock stock);

    Stock toStock(StockResponseDTO stockResponseDTO);

    Stock toStock(StockCreateDTO stockCreateDTO);

    StockUpdateDTO toStockUpdate(Stock stock);
}
