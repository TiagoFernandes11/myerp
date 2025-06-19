package br.erp.myerp.domain.stockmovement.service;

import br.erp.myerp.domain.stockmovement.client.product.ProductClient;
import br.erp.myerp.domain.stockmovement.client.stock.StockClient;
import br.erp.myerp.domain.stockmovement.dto.stock.StockDTO;
import br.erp.myerp.domain.stockmovement.dto.stockmovement.StockMovementCreateDTO;
import br.erp.myerp.domain.stockmovement.dto.stockmovement.StockMovementResponseDTO;
import br.erp.myerp.domain.stockmovement.entity.StockMovement;
import br.erp.myerp.domain.stockmovement.entity.StockMovementItem;
import br.erp.myerp.domain.stockmovement.enums.MovementType;
import br.erp.myerp.domain.stockmovement.mapper.StockMovementMapper;
import br.erp.myerp.domain.stockmovement.mapper.StockMovementStockMapper;
import br.erp.myerp.domain.stockmovement.repository.StockMovementRepository;
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
        return stockMovementRepository.findAll().stream().map((stockMovement) -> stockMovementMapper.toStockMovementDTO(stockMovement)).toList();
    }

    public StockMovementResponseDTO findStockMovement(Long id){
        return stockMovementMapper
                .toStockMovementDTO(stockMovementRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Stock movement not found for id: " + id))
                );
    }

    public void create(StockMovementCreateDTO stockMovementCreateDTO){
        StockMovement stockMovement = stockMovementMapper.toStockMovement(stockMovementCreateDTO);

        for(StockMovementItem item : stockMovementCreateDTO.getItems()){
            StockDTO stock = stockClient.getByProductId(item.getProductId());

            if (stockMovementCreateDTO.getType() == MovementType.OUT) {
                if (stock.getQuantity() < item.getQuantity()) {
                    throw new IllegalArgumentException("The stock does not have sufficient quantity");
                }
                stock.addQuantity(item.getQuantity() * -1);
            } else if (stockMovementCreateDTO.getType() == MovementType.IN) {
                stock.addQuantity(item.getQuantity());
            }

            stockClient.update(stockMapper.toStockUpdateDTO(stock));
            item.setStockMovement(stockMovement);
            stockMovement.setTimestamp(LocalDateTime.now());
        }
        stockMovementRepository.save(stockMovement);
    }

//    public void update(StockMovementUpdateDTO stockMovementUpdateDTO){
//        StockMovement stockMovement = getStockMovementById(stockMovementUpdateDTO.getId());
//        StockDTO stock = stockClient.getById(stockMovement.getStockId());
//
//        int diff =  stockMovement.getQuantity() - stockMovementUpdateDTO.getQuantity();
//
//        if(diff + stock.getQuantity() < 0){
//            throw new IllegalArgumentException("The stock does not have sufficient quantity");
//        } else {
//            stock.addQuantity(diff);
//            stockClient.update(stockMapper.toStockUpdateDTO(stock));
//            stockMovement = stockMovementMapper.toStockMovement(stockMovementUpdateDTO);
//            stockMovementRepository.save(stockMovement);
//        }
//    }
//
//    public void delete(Long id){
//        StockMovement stockMovement = getStockMovementById(id);
//        StockDTO stock = stockClient.getById(stockMovement.getStockId());
//
//        if(stockMovement.getType() == MovementType.IN){
//            stock.addQuantity(stockMovement.getQuantity() * -1);
//        }else {
//            stock.addQuantity(stockMovement.getQuantity());
//        }
//        stockClient.update(stockMapper.toStockUpdateDTO(stock));
//        stockMovementRepository.delete(stockMovement);
//    }

    private StockMovement getStockMovementById(Long id){
        return stockMovementRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StockMovement not founded for id: #" + id));
    }
}
