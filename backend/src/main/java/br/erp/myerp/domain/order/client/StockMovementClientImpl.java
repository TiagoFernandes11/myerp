package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("stockMovementClientImplForOrder")
public class StockMovementClientImpl implements StockMovementClient{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public StockMovementResponseDTO create(StockMovementCreateDTO stockMovementCreateDTO) {
        return restTemplate
                .postForEntity("http://localhost:8080/api/stock-movement", stockMovementCreateDTO, StockMovementResponseDTO.class, 1).getBody();
    }

    @Override
    public void update(Long id, StockMovementResponseDTO stockMovement) {
        restTemplate.put("http://localhost:8080/api/stock-movement/{id}", stockMovement, id);
    }
}
