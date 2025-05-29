package br.erp.myerp.backend.domain.stockmovement.service;

import br.erp.myerp.backend.domain.stock.entity.Stock;
import br.erp.myerp.backend.domain.stock.mapper.StockMapper;
import br.erp.myerp.backend.domain.stockmovement.client.product.ProductClient;
import br.erp.myerp.backend.domain.stockmovement.client.stock.StockClient;
import br.erp.myerp.backend.domain.stockmovement.dto.product.ProductDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementCreateDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.backend.domain.stockmovement.entity.StockMovement;
import br.erp.myerp.backend.domain.stockmovement.enums.MovementType;
import br.erp.myerp.backend.domain.stockmovement.mapper.StockMovementMapper;
import br.erp.myerp.backend.domain.stockmovement.repository.StockMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("productClientForStockMovement")
    private ProductClient productClient;

    @Autowired
    private StockClient stockClient;

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

    public StockMovementResponseDTO findStockMovementByProduct(Long productId){
        ProductDTO product = productClient.getProduct(productId);
        Stock stock = stockClient.getByProductId(productId);
        StockMovement stockMovement = stockMovementRepository.findByStockId(stock.getId())
                .orElseThrow(() -> new EntityNotFoundException("Stock movement not found for product: " + product.getName()));
        return stockMovementMapper.toStockMovementDTO(stockMovement);
    }

    public void create(StockMovementCreateDTO stockMovementCreateDTO){
        Stock stock = stockClient.getById(stockMovementCreateDTO.getStockId());

        if (stockMovementCreateDTO.getType() == MovementType.OUT) {
            if (stock.getQuantity() < stockMovementCreateDTO.getQuantity()) {
                throw new IllegalArgumentException("The stock does not have sufficient quantity");
            }
            stock.addQuantity(stockMovementCreateDTO.getQuantity() * -1);
        } else if (stockMovementCreateDTO.getType() == MovementType.IN) {
            stock.addQuantity(stockMovementCreateDTO.getQuantity());
        }

        stockClient.update(stockMapper.toStockUpdate(stock));

        StockMovement stockMovement = new StockMovement();
        stockMovement.setStockId(stock.getId());
        stockMovement.setType(stockMovementCreateDTO.getType());
        stockMovement.setQuantity(stockMovementCreateDTO.getQuantity());
        stockMovement.setDescription(stockMovementCreateDTO.getDescription());
        stockMovement.setTimestamp(LocalDateTime.now());

        stockMovementRepository.save(stockMovement);
    }
}
