package br.erp.myerp.backend.domain.stockmovement.service;

import br.erp.myerp.backend.domain.stockmovement.client.product.ProductClient;
import br.erp.myerp.backend.domain.stockmovement.client.stock.StockClient;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementCreateDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementUpdateDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.product.ProductDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.stock.StockDTO;
import br.erp.myerp.backend.domain.stockmovement.entity.StockMovement;
import br.erp.myerp.backend.domain.stockmovement.enums.MovementType;
import br.erp.myerp.backend.domain.stockmovement.mapper.StockMovementMapper;
import br.erp.myerp.backend.domain.stockmovement.mapper.StockMovementStockMapper;
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
    private StockMovementStockMapper stockMapper;

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
        StockDTO stock = stockClient.getByProductId(productId);
        StockMovement stockMovement = stockMovementRepository.findByStockId(stock.getId())
                .orElseThrow(() -> new EntityNotFoundException("Stock movement not found for product: " + product.getName()));
        return stockMovementMapper.toStockMovementDTO(stockMovement);
    }

    public void create(StockMovementCreateDTO stockMovementCreateDTO){
        StockDTO stock = stockClient.getById(stockMovementCreateDTO.getStockId());

        if (stockMovementCreateDTO.getType() == MovementType.OUT) {
            if (stock.getQuantity() < stockMovementCreateDTO.getQuantity()) {
                throw new IllegalArgumentException("The stock does not have sufficient quantity");
            }
            stock.addQuantity(stockMovementCreateDTO.getQuantity() * -1);
        } else if (stockMovementCreateDTO.getType() == MovementType.IN) {
            stock.addQuantity(stockMovementCreateDTO.getQuantity());
        }

        stockClient.update(stockMapper.toStockUpdateDTO(stock));

        StockMovement stockMovement = stockMovementMapper.toStockMovement(stockMovementCreateDTO);
        stockMovement.setStockId(stock.getId());
        stockMovement.setTimestamp(LocalDateTime.now());

        stockMovementRepository.save(stockMovement);
    }

    public void update(StockMovementUpdateDTO stockMovementUpdateDTO){
        StockMovement stockMovement = getStockMovementById(stockMovementUpdateDTO.getId());
        StockDTO stock = stockClient.getById(stockMovement.getStockId());

        int diff =  stockMovement.getQuantity() - stockMovementUpdateDTO.getQuantity();

        if(diff + stock.getQuantity() < 0){
            throw new IllegalArgumentException("The stock does not have sufficient quantity");
        } else {
            stock.addQuantity(diff);
            stockClient.update(stockMapper.toStockUpdateDTO(stock));
            stockMovement = stockMovementMapper.toStockMovement(stockMovementUpdateDTO);
            stockMovementRepository.save(stockMovement);
        }
    }

    public void delete(Long id){
        StockMovement stockMovement = getStockMovementById(id);
        StockDTO stock = stockClient.getById(stockMovement.getStockId());

        stock.addQuantity(stock.getQuantity() * -1);
        stockClient.update(stockMapper.toStockUpdateDTO(stock));
        stockMovementRepository.save(stockMovement);
    }

    private StockMovement getStockMovementById(Long id){
        return stockMovementRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StockMovement not founded for id: #" + id));
    }
}
