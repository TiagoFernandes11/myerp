package br.erp.myerp.backend.stock.controller;

import br.erp.myerp.backend.stock.dto.stockMovement.StockMoventCreateDTO;
import br.erp.myerp.backend.stock.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock-movement")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @PostMapping
    public void create(@RequestBody @Valid StockMoventCreateDTO stockMovement){

    }
}
