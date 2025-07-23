package br.erp.myerp.domain.stock.service;

import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemUpdateDTO;
import br.erp.myerp.domain.stock.entity.StockMovement;
import br.erp.myerp.domain.stock.entity.StockMovementItem;
import br.erp.myerp.domain.stock.mapper.StockMovementItemMapper;
import br.erp.myerp.domain.stock.mapper.StockMovementMapper;
import br.erp.myerp.domain.stock.repository.StockMovementItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class StockMovementItemService {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockMovementService stockMovementService;

    @Autowired
    private StockMovementItemRepository stockMovementItemRepository;

    @Autowired
    private StockMovementMapper stockMovementMapper;

    @Autowired
    private StockMovementItemMapper mapper;

    public List<StockMovementItemResponseDTO> findAll() {
        return stockMovementItemRepository.findAll().stream()
                .map(item -> mapper.toStockMovementItemResponseDTO(item)).toList();
    }

    public StockMovementItemResponseDTO findById(Long id) {
        return mapper.toStockMovementItemResponseDTO(stockMovementItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StockMovementItem not found with id: " + id)));
    }

    public void create(@Valid StockMovementItemCreateDTO stockMovementItemCreateDTO) {
        StockMovement stockMovement = stockMovementMapper
                .toStockMovement(
                        stockMovementService.findById(stockMovementItemCreateDTO.getStockMovementId())
                );

        StockMovementItem item = mapper.toStockMovementItem(stockMovementItemCreateDTO);
        stockMovementItemRepository.save(item);

        stockMovement.getItems().add(item);
        stockMovementService.update(stockMovementMapper.toStockMovementUpdateDTO(stockMovement));
    }

    public void update(@Valid StockMovementItemUpdateDTO stockMovementItemUpdateDTO){
        StockMovement stockMovement = stockMovementMapper
                .toStockMovement(
                        stockMovementService.findById(stockMovementItemUpdateDTO.getStockMovementId())
                );

        StockMovementItem item = mapper.toStockMovementItem(stockMovementItemUpdateDTO);
        stockMovementItemRepository.save(item);

        StockMovementItem persisted = stockMovement.findStockMovementItemByProductId(item.getProductId());
        persisted.setStockMovementId(stockMovementItemUpdateDTO.getStockMovementId());
        persisted.setQuantity(stockMovementItemUpdateDTO.getQuantity());
        persisted.setProductId(stockMovementItemUpdateDTO.getProductId());

        stockMovementService.update(stockMovementMapper.toStockMovementUpdateDTO(stockMovement));
    }

    public void delete(Long id){
        StockMovementItem stockMovementItem = stockMovementItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("StockMovementItem not found with id: " + id));
        StockMovement stockMovement = stockMovementMapper.toStockMovement(stockMovementService.findById(id));
        StockMovementItem persisted = stockMovement.findStockMovementItemByProductId(id);

        stockMovement.getItems().remove(persisted);
        stockMovementService.update(stockMovementMapper.toStockMovementUpdateDTO(stockMovement));

        stockMovementItemRepository.deleteById(id);
    }
}
