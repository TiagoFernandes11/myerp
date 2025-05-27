package br.erp.myerp.backend.cruds.stock.service;

import br.erp.myerp.backend.cruds.product.entity.Product;
import br.erp.myerp.backend.cruds.stock.dto.StockCreateDTO;
import br.erp.myerp.backend.cruds.stock.dto.StockResponseDTO;
import br.erp.myerp.backend.cruds.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.cruds.stock.entity.Stock;
import br.erp.myerp.backend.cruds.stock.mapper.StockMapper;
import br.erp.myerp.backend.cruds.stock.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    public List<StockResponseDTO> getAll() {
        return stockRepository.findAllDto();
    }

    public StockResponseDTO get(Long id) {
        return stockMapper.toStockDTO(
                stockRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded with id: " + id)
                ));
    }

    public StockResponseDTO get(Product product) {
        return stockMapper.toStockDTO(
                stockRepository.findByProduct(product).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded for product:  " + product.getName())));
    }

    public void create(StockCreateDTO stockCreateDTO) {
        Stock stock = stockMapper.toStock(stockCreateDTO);
        stockRepository.save(stock);
    }

    public void update(StockUpdateDTO stockUpdateDTO){
        Stock stock = stockMapper.toStock(this.get(stockUpdateDTO.getProduct()));
        stock.setQuantity(stockUpdateDTO.getQuantity());
        stockRepository.save(stock);
    }

    public void delete(Long id){
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Stock not founded with id: " + id)
        );
        stockRepository.delete(stock);
    }
}
