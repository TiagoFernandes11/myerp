package br.erp.myerp.backend.cruds.stockmovement.service;

import br.erp.myerp.backend.cruds.product.entity.Product;
import br.erp.myerp.backend.cruds.stock.service.StockService;
import br.erp.myerp.backend.cruds.stockmovement.client.ProductClient;
import br.erp.myerp.backend.cruds.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.backend.cruds.stockmovement.dto.StockMovementCreateDTO;
import br.erp.myerp.backend.cruds.stock.entity.Stock;
import br.erp.myerp.backend.cruds.stockmovement.entity.StockMovement;
import br.erp.myerp.backend.cruds.stockmovement.enums.MovementType;
import br.erp.myerp.backend.cruds.stock.mapper.StockMapper;
import br.erp.myerp.backend.cruds.stockmovement.mapper.StockMovementMapper;
import br.erp.myerp.backend.cruds.stockmovement.repository.StockMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private StockMovementMapper stockMovementMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private StockMapper stockMapper;

    public List<StockMovementResponseDTO> findAll(){
        return stockMovementRepository.findAllDto();
    }

    public StockMovementResponseDTO findStockMovement(Long id){
        return stockMovementMapper
                .toStockMovementDTO(stockMovementRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Stock movement not found for id: " + id))
                );
    }

    public StockMovementResponseDTO findStockMovement(Product product){
        Stock stock = stockMapper.toStock(stockService.get(product));
        StockMovement stockMovement = stockMovementRepository.findByStock(stock)
                .orElseThrow(() -> new EntityNotFoundException("Stock movement not found for product: " + product.getName()));
        return stockMovementMapper.toStockMovementDTO(stockMovement);
    }

    public void create(StockMovementCreateDTO stockMovementCreateDTO){
        Product product = productClient.getProduct(stockMovementCreateDTO.getStock().getProduct().getId());

        Stock stock = stockMapper.toStock(stockService.get(product));

        if (stockMovementCreateDTO.getType() == MovementType.OUT) {
            if (stock.getQuantity() < stockMovementCreateDTO.getQuantity()) {
                throw new IllegalArgumentException("The stock does not have sufficient quantity");
            }
            stock.addQuantity(stockMovementCreateDTO.getQuantity() * -1);
        } else if (stockMovementCreateDTO.getType() == MovementType.IN) {
            stock.addQuantity(stockMovementCreateDTO.getQuantity());
        }

        stockService.update(stockMapper.toStockUpdate(stock));

        StockMovement stockMovement = new StockMovement();
        stockMovement.setStock(stock);
        stockMovement.setType(stockMovementCreateDTO.getType());
        stockMovement.setDescription(stockMovementCreateDTO.getDescription());
        stockMovement.setTimestamp(LocalDateTime.now());

        stockMovementRepository.save(stockMovement);
    }
}
