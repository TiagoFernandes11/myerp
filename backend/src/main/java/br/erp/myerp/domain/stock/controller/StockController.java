package br.erp.myerp.domain.stock.controller;

import br.erp.myerp.domain.stock.client.product.ProductClient;
import br.erp.myerp.common.security.response.Response;
import br.erp.myerp.domain.stock.dto.StockCreateDTO;
import br.erp.myerp.domain.stock.dto.StockResponseDTO;
import br.erp.myerp.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.domain.stock.service.StockService;
import jakarta.validation.Valid;
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

    @Autowired
    private ProductClient productClient;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStock(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.get(id));
    }

    @GetMapping("/by-product/{id}")
    public ResponseEntity<StockResponseDTO> getStockByProduct(@PathVariable Long idProduct){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.get(idProduct));
    }

    @PostMapping
    public ResponseEntity<String> createStock(@RequestBody @Valid StockCreateDTO stockCreateDTO){
        stockService.create(stockCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Stock for product: #" + stockCreateDTO.getProductId() + " was successfully created");
    }

    @PutMapping
    public ResponseEntity<String> updateStock(@RequestBody @Valid StockUpdateDTO stockUpdateDTO){
        stockService.update(stockUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Stock for product: #" + stockUpdateDTO.getProductId() + " was successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id){
        stockService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Stock with id: " + id + " was successfully deleted");
    }
}
