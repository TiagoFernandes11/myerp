package br.erp.myerp.domain.stock.service;

import br.erp.myerp.domain.stock.client.product.ProductClient;
import br.erp.myerp.domain.stock.dto.order.OrderItemCreateDTO;
import br.erp.myerp.domain.stock.dto.stock.StockCreateDTO;
import br.erp.myerp.domain.stock.dto.stock.StockResponseDTO;
import br.erp.myerp.domain.stock.dto.stock.StockUpdateDTO;
import br.erp.myerp.domain.stock.entity.Stock;
import br.erp.myerp.domain.stock.mapper.StockMapper;
import br.erp.myerp.domain.stock.repository.StockRepository;
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

    public StockResponseDTO getById(Long id) {
        return stockMapper.toStockDTO(
                stockRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded with id: " + id)
                ));
    }

    public StockResponseDTO getByProductId(Long productId) {
        if(productId == null || productId < 1){
            return new StockResponseDTO();
        }
        return stockRepository.findByProductId(productId).orElseThrow(
                        () -> new EntityNotFoundException("Stock not founded for product:  " + productId));
    }

    public void create(StockCreateDTO stockCreateDTO) {
        Stock stock = stockMapper.toStock(stockCreateDTO);
        stockRepository.save(stock);
    }

    public Stock update(StockUpdateDTO stockUpdateDTO){
        Stock stock = stockMapper.toStock(this.getByProductId(stockUpdateDTO.getProductId()));
        stock.setQuantity(stockUpdateDTO.getQuantity());
        return stockRepository.save(stock);
    }

    public void delete(Long id){
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Stock not founded with id: " + id)
        );
        stockRepository.delete(stock);
    }

    public boolean checkStock(List<OrderItemCreateDTO> orderItems){

        for(OrderItemCreateDTO item : orderItems){
            StockResponseDTO stock = getByProductId(item.getProductId());
            int quantity = item.getQuantity();

            if(stock.getQuantity() - quantity < 0){
                throw new IllegalArgumentException("The stock does not have sufficient quantity for product: " + item.getProductId());
            }
        }

        return true;
    }
}
