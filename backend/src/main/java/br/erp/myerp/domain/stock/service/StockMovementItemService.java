package br.erp.myerp.domain.stock.service;

import br.erp.myerp.domain.stock.repository.StockMovementItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMovementItemService {

    @Autowired
    private StockMovementItemRepository stockMovementItemRepository;
}
