package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class StockMovementItemClientImpl implements StockMovementItemClient{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public StockMovementItemResponseDTO save(StockMovementItemCreateDTO si) {
        return restTemplate
                .postForEntity("http://localhost:8080/api/stock-movement-item", si, StockMovementItemResponseDTO.class).getBody();
    }
}
