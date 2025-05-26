package br.erp.myerp.backend.stock.service;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.dto.StockDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import br.erp.myerp.backend.stock.mapper.StockMapper;
import br.erp.myerp.backend.stock.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    public List<StockDTO> getAll() {
        return stockRepository.findAllDto();
    }

    public StockDTO get(Long id) {
        return stockMapper.toStockDTO(
                stockRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded with id: " + id)
                ));
    }

    public StockDTO get(Product product) {
        return stockMapper.toStockDTO(
                stockRepository.findByProduct(product).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded for product:  " + product.getName())));
    }

    public void create(StockDTO stockDTO) {
        Stock stock = stockMapper.toStock(stockDTO);
        stockRepository.save(stock);
    }

    public void update(StockDTO stockDTO){
        Stock stock = stockMapper.toStock(this.get(stockDTO.getId()));
        stock.setQuantity(stockDTO.getQuantity());
        stockRepository.save(stock);
    }

    public void delete(StockDTO stockDTO){
        Stock stock = stockMapper.toStock(this.get(stockDTO.getId()));
        stockRepository.delete(stock);
    }
}
