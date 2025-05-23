package br.erp.myerp.backend.stock.controller;

import br.erp.myerp.backend.stock.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;
}
