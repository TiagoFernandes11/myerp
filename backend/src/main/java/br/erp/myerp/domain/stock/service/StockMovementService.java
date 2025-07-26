package br.erp.myerp.domain.stock.service;

import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.stock.client.product.ProductClient;
import br.erp.myerp.domain.stock.dto.stock.StockResponseDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementCreateDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementResponseDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementUpdateDTO;
import br.erp.myerp.domain.stock.entity.Stock;
import br.erp.myerp.domain.stock.entity.StockMovement;
import br.erp.myerp.domain.stock.entity.StockMovementItem;
import br.erp.myerp.domain.stock.enums.MovementType;
import br.erp.myerp.domain.stock.mapper.StockMapper;
import br.erp.myerp.domain.stock.mapper.StockMovementMapper;
import br.erp.myerp.domain.stock.repository.StockMovementRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private StockMovementMapper stockMovementMapper;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockMapper stockMapper;

    public List<StockMovementResponseDTO> findAll() {
        return stockMovementRepository.findAll().stream().map((stockMovement) -> stockMovementMapper.toStockMovementDTO(stockMovement)).toList();
    }

    public StockMovementResponseDTO findById(Long id) {
        return stockMovementMapper.toStockMovementDTO(stockMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stock movement not found for id: " + id)));
    }

    public StockMovement create(@Valid StockMovementCreateDTO stockMovementCreateDTO) {
        StockMovement stockMovement = stockMovementMapper.toStockMovement(stockMovementCreateDTO);
        stockMovement.setTimestamp(LocalDateTime.now());

        for (StockMovementItemCreateDTO item : stockMovementCreateDTO.getItems()) {
            Stock stock = stockMapper.toStock(stockService.getByProductId(item.getProductId()));

            if (stockMovementCreateDTO.getType() == MovementType.OUT) {
                if (stock.getQuantity() < item.getQuantity()) {
                    throw new IllegalArgumentException("The stock does not have sufficient quantity");
                }
                stock.addQuantity(item.getQuantity() * -1);
            } else if (stockMovementCreateDTO.getType() == MovementType.IN) {
                stock.addQuantity(item.getQuantity());
            }

            stockService.update(stockMapper.toStockUpdateDTO(stock));
        }
        return stockMovementRepository.save(stockMovement);
    }

    public StockMovement update(@Valid StockMovementUpdateDTO stockMovementUpdateDTO) {
        StockMovement stockMovement = getStockMovementById(stockMovementUpdateDTO.getId());
        int i = 0;

        for (StockMovementItem item : stockMovement.getItems()) {
            StockResponseDTO stock = stockService.getByProductId(item.getProductId());

            int itemQuantity = stockMovement.getType() == MovementType.IN ? item.getQuantity() : -1 * item.getQuantity();
            int updateQuantity = stockMovementUpdateDTO.getType() == MovementType.IN ?
                    stockMovementUpdateDTO.getItems().get(i).getQuantity() : -1 * stockMovementUpdateDTO.getItems().get(i).getQuantity();

            int diff = updateQuantity - itemQuantity;

            if (diff + stock.getQuantity() < 0) {
                throw new IllegalArgumentException("The stock does not have sufficient quantity");
            } else {
                item.addQuantity(diff);
                stock.addQuantity(diff);
                stockService.update(stockMapper.toStockUpdateDTO(stockMapper.toStock(stock)));
            }
            item.setStockMovementId(stockMovement.getId());
        }
        stockMovement = stockMovementMapper.toStockMovement(stockMovementUpdateDTO);
        return stockMovementRepository.save(stockMovement);
    }

    public void delete(Long id){
        StockMovement stockMovement = getStockMovementById(id);

        for(StockMovementItem item : stockMovement.getItems()){
            StockResponseDTO stock = stockService.getByProductId(item.getProductId());

            if(stockMovement.getType() == MovementType.IN){
                stock.addQuantity(item.getQuantity() * -1);
            }else {
                stock.addQuantity(item.getQuantity());
            }
            stockService.update(stockMapper.toStockUpdateDTO(stock));
        }
        stockMovementRepository.delete(stockMovement);
    }

    private StockMovement getStockMovementById(Long id) {
        return stockMovementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("StockMovement not founded for id: #" + id));
    }
}
