package br.erp.myerp.backend.stock.service;

import br.erp.myerp.backend.stock.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;
}
