package br.erp.myerp.backend.stock.mapper;

import br.erp.myerp.backend.stock.dto.stock.StockDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDTO toStockDTO(Stock stock);

    Stock toStock(StockDTO stockDTO);
}
