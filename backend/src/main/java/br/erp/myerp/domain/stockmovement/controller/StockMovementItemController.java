package br.erp.myerp.domain.stockmovement.controller;

import br.erp.myerp.domain.stockmovement.dto.stockmovementitem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.stockmovement.entity.StockMovementItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movement-item")
public class StockMovementItemController {
}
