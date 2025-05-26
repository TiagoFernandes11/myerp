package br.erp.myerp.backend.stock.controller;

import br.erp.myerp.backend.response.Response;
import br.erp.myerp.backend.stock.dto.StockDTO;
import br.erp.myerp.backend.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.get(id));
    }

    @PostMapping
    public ResponseEntity<Response> createStock(@RequestBody StockDTO stockDTO){
        stockService.create(stockDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new Response(HttpStatus.CREATED, "Stock for product: " + stockDTO.getProduct().getName() + " was successfully created")
                );
    }

    @PutMapping
    public ResponseEntity<Response> updateStock(@RequestBody StockDTO stockDTO){
        stockService.update(stockDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new Response(HttpStatus.OK, "Stock for product: " + stockDTO.getProduct().getName() + " was successfully created")
                );
    }
}
