package br.erp.myerp.backend.domain.stock.service;

import br.erp.myerp.backend.domain.stock.client.product.ProductClient;
import br.erp.myerp.backend.domain.stock.dto.StockCreateDTO;
import br.erp.myerp.backend.domain.stock.dto.StockResponseDTO;
import br.erp.myerp.backend.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.domain.stock.entity.Stock;
import br.erp.myerp.backend.domain.stock.mapper.StockMapper;
import br.erp.myerp.backend.domain.stock.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    @Qualifier("productClientForStock")
    private ProductClient productClient;

    public List<StockResponseDTO> getAll() {
        return stockRepository.findAllDto();
    }

    public StockResponseDTO get(Long id) {
        return stockMapper.toStockDTO(
                stockRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded with id: " + id)
                ));
    }

    public StockResponseDTO getByProductId(Long productId) {
        return stockRepository.findByProductId(productId).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded for product:  " + productId));
    }

    public void create(StockCreateDTO stockCreateDTO) {
        Stock stock = stockMapper.toStock(stockCreateDTO);
        stockRepository.save(stock);
    }

    public void update(StockUpdateDTO stockUpdateDTO){
        Stock stock = stockMapper.toStock(this.getByProductId(stockUpdateDTO.getProductId()));
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
