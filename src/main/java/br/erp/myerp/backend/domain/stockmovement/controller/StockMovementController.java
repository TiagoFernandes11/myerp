package br.erp.myerp.backend.domain.stockmovement.controller;

import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementCreateDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementUpdateDTO;
import br.erp.myerp.backend.domain.stockmovement.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-movement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @PostMapping
    public void create(@RequestBody @Valid StockMovementCreateDTO stockMovement){
        stockMovementService.create(stockMovement);
    }

    @PutMapping
    public void update(@RequestBody @Valid StockMovementUpdateDTO stockMovementUpdateDTO){
        stockMovementService.update(stockMovementUpdateDTO);
    }
}
