package br.erp.myerp.backend.domain.stockmovement.controller;

import br.erp.myerp.backend.common.response.Response;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementCreateDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementResponseDTO;
import br.erp.myerp.backend.domain.stockmovement.dto.StockMovementUpdateDTO;
import br.erp.myerp.backend.domain.stockmovement.entity.StockMovement;
import br.erp.myerp.backend.domain.stockmovement.service.StockMovementService;
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
        StockMovementResponseDTO stockMovement = stockMovementService.findStockMovement(id);
        return ResponseEntity.status(HttpStatus.OK).body(stockMovement);
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<StockMovementResponseDTO> findByProductId(@PathVariable Long productId){
        StockMovementResponseDTO stockMovement = stockMovementService.findStockMovementByProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(stockMovement);
    }

    @PostMapping
    public ResponseEntity<StockMovementCreateDTO> create(@RequestBody @Valid StockMovementCreateDTO stockMovement){
        stockMovementService.create(stockMovement);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockMovement);
    }

    @PutMapping
    public ResponseEntity<StockMovementUpdateDTO> update(@RequestBody @Valid StockMovementUpdateDTO stockMovementUpdateDTO){
        stockMovementService.update(stockMovementUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(stockMovementUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id){
        stockMovementService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(HttpStatus.OK, "Stock movement with id: #" + id + " was successfully deleted"));
    }
}
