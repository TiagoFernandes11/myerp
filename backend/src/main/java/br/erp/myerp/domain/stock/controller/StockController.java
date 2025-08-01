package br.erp.myerp.domain.stock.controller;

import br.erp.myerp.domain.stock.client.product.ProductClient;
import br.erp.myerp.domain.stock.dto.order.OrderItemCreateDTO;
import br.erp.myerp.domain.stock.dto.stock.StockCreateDTO;
import br.erp.myerp.domain.stock.dto.stock.StockResponseDTO;
import br.erp.myerp.domain.stock.dto.stock.StockUpdateDTO;
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
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getById(id));
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<StockResponseDTO> getStockByProduct(@PathVariable Long productId){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getById(productId));
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

    @PostMapping("/check-stock")
    public ResponseEntity<Boolean> checkStock(@RequestBody List<OrderItemCreateDTO> orderItens){
        return ResponseEntity.ok().body(stockService.checkStock(orderItens));
    }
}
