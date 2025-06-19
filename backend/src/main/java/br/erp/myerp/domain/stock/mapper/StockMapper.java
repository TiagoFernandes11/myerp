package br.erp.myerp.domain.stock.mapper;

import br.erp.myerp.domain.stock.dto.stock.StockCreateDTO;
import br.erp.myerp.domain.stock.dto.stock.StockResponseDTO;
import br.erp.myerp.domain.stock.dto.stock.StockUpdateDTO;
import br.erp.myerp.domain.stock.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockUpdateDTO toStockUpdateDTO(Stock stock);

    StockResponseDTO toStockDTO(Stock stock);

    Stock toStock(StockResponseDTO stockResponseDTO);

    Stock toStock(StockCreateDTO stockCreateDTO);

    StockUpdateDTO toStockUpdate(Stock stock);
}
