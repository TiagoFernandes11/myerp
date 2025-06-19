package br.erp.myerp.domain.stockmovement.service;

import br.erp.myerp.domain.stockmovement.repository.StockMovementItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementItemService {

    @Autowired
    private StockMovementItemRepository stockMovementItemRepository;
}
