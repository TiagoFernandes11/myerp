package br.erp.myerp.domain.order.controller;

import br.erp.myerp.domain.order.dto.order.OrderResponseDTO;
import br.erp.myerp.domain.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final int PAGE_SIZE = 20;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{pageNum}")
    public ResponseEntity<List<OrderResponseDTO>> getAll(@RequestParam(required = false, defaultValue = "0") int pageNum){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll(PAGE_SIZE, pageNum));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getById(@PathVariable(required = true) long id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }
}
