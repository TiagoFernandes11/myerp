package br.erp.myerp.backend.cruds.stock.mapper;

import br.erp.myerp.backend.cruds.stock.dto.StockCreateDTO;
import br.erp.myerp.backend.cruds.stock.dto.StockResponseDTO;
import br.erp.myerp.backend.cruds.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.cruds.stock.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockResponseDTO toStockDTO(Stock stock);

    Stock toStock(StockResponseDTO stockResponseDTO);

    Stock toStock(StockCreateDTO stockCreateDTO);

    StockUpdateDTO toStockUpdate(Stock stock);
}
