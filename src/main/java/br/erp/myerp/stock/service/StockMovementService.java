package br.erp.myerp.stock.service;

import br.erp.myerp.stock.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;
}
