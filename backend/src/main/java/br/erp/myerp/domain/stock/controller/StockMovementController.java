package br.erp.myerp.domain.stock.controller;

import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementCreateDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementResponseDTO;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementUpdateDTO;
import br.erp.myerp.domain.stock.entity.StockMovement;
import br.erp.myerp.domain.stock.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<List<StockMovementResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(stockMovementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponseDTO> findById(@PathVariable Long id){
        StockMovementResponseDTO stockMovement = stockMovementService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(stockMovement);
    }

    @PostMapping
    public ResponseEntity<StockMovement> create(@RequestBody StockMovementCreateDTO stockMovement){
        StockMovement sm = stockMovementService.create(stockMovement);
        return ResponseEntity.status(HttpStatus.CREATED).body(sm);
    }

    @PutMapping
    public ResponseEntity<StockMovementUpdateDTO> update(@RequestBody @Valid StockMovementUpdateDTO stockMovementUpdateDTO){
        try{
            stockMovementService.update(stockMovementUpdateDTO);
            return ResponseEntity.status(HttpStatus.OK).body(stockMovementUpdateDTO);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        stockMovementService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Stock movement with id: #" + id + " was successfully deleted");
    }
}
