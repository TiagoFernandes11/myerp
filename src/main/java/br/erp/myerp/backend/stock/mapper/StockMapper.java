package br.erp.myerp.backend.stock.mapper;

import br.erp.myerp.backend.stock.dto.stock.StockCreateDTO;
import br.erp.myerp.backend.stock.dto.stock.StockResponseDTO;
import br.erp.myerp.backend.stock.dto.stock.StockUpdateDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockResponseDTO toStockDTO(Stock stock);

    Stock toStock(StockResponseDTO stockResponseDTO);

    Stock toStock(StockCreateDTO stockCreateDTO);

    StockUpdateDTO toStockUpdate(Stock stock);
}
