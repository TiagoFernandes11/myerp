package br.erp.myerp.domain.stock.controller;

import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.stock.dto.stockmovementitem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.stock.mapper.StockMovementItemMapper;
import br.erp.myerp.domain.stock.service.StockMovementItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movement-item")
public class StockMovementItemController {

    private final int PAGE_SIZE = 20;

    @Autowired
    private StockMovementItemService stockMovementItemService;

    @Autowired
    private StockMovementItemMapper stockMovementItemMapper;

    @GetMapping
    public ResponseEntity<List<StockMovementItemResponseDTO>> findAll(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                                                                      @RequestParam(name = "value", required = false, defaultValue = "") String value,
                                                                      @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
        return ResponseEntity.ok().body(stockMovementItemService.findAll(PAGE_SIZE, pageNum, filter, value));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementItemResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(stockMovementItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StockMovementItemResponseDTO> create(@RequestBody StockMovementItemCreateDTO stockMovementItem){
        return ResponseEntity.ok().body(stockMovementItemService.create(stockMovementItem));
    }
}
