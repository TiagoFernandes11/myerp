package br.erp.myerp.backend.stock.service;

import br.erp.myerp.backend.product.entity.Product;
import br.erp.myerp.backend.stock.dto.stockMovement.StockMoventCreateDTO;
import br.erp.myerp.backend.stock.entity.Stock;
import br.erp.myerp.backend.stock.entity.StockMovement;
import br.erp.myerp.backend.stock.enums.MovementType;
import br.erp.myerp.backend.stock.mapper.StockMapper;
import br.erp.myerp.backend.stock.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockMapper stockMapper;

    public void create(StockMoventCreateDTO stockMoventCreateDTO){
        Product product = restTemplate.getForObject(
                "http://localhost:8080/api/product/{id}", Product.class, stockMoventCreateDTO.getProduct().getId());

        Stock stock = stockMapper.toStock(stockService.get(product));

        if(stock.getQuantity() < stockMoventCreateDTO.getQuantity()){
            throw new IllegalArgumentException("The stock does not have a sufficient quantity");
        }

        if(stockMoventCreateDTO.getType() == MovementType.OUT){
            stock.addQuantity(stockMoventCreateDTO.getQuantity() * -1);
        }

        stockService.update(stockMapper.toStockUpdate(stock));

        StockMovement stockMovement = new StockMovement();
        stockMovement.setStock(stock);
        stockMovement.setType(stockMoventCreateDTO.getType());
        stockMovement.setDescription(stockMoventCreateDTO.getDescription());
        stockMovement.setTimestamp(LocalDateTime.now());

        stockMovementRepository.save(stockMovement);
    }
}
