package br.erp.myerp.backend.cruds.stock.controller;

import br.erp.myerp.backend.cruds.stock.client.product.ProductClient;
import br.erp.myerp.backend.response.Response;
import br.erp.myerp.backend.cruds.stock.dto.StockCreateDTO;
import br.erp.myerp.backend.cruds.stock.dto.StockResponseDTO;
import br.erp.myerp.backend.cruds.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.cruds.stock.service.StockService;
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
    public ResponseEntity<Response> createStock(@RequestBody @Valid StockCreateDTO stockCreateDTO){
        stockService.create(stockCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new Response(HttpStatus.CREATED, "Stock for product: " + stockCreateDTO.getProduct().getName() + " was successfully created")
                );
    }

    @PutMapping
    public ResponseEntity<Response> updateStock(@RequestBody @Valid StockUpdateDTO stockUpdateDTO){
        stockService.update(stockUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new Response(HttpStatus.OK, "Stock for product: " + stockUpdateDTO.getProduct().getName() + " was successfully created")
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteStock(@PathVariable Long id){
        stockService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Stock with id: " + id + " was successfully deleted"));
    }
}
