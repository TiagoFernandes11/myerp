package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.order.security.InternalTokenProviderForOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("stockMovementClientImplForOrder")
public class StockMovementClientImpl implements StockMovementClient{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForOrder tokenProvider;

    @Override
    public StockMovementResponseDTO create(StockMovementCreateDTO stockMovementCreateDTO) {
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<StockMovementCreateDTO> entity = new HttpEntity<>(stockMovementCreateDTO, headers);
        return restTemplate
                .exchange("http://localhost:8080/api/stock-movement", HttpMethod.POST, entity, StockMovementResponseDTO.class).getBody();
    }

    @Override
    public void update(Long id, StockMovementResponseDTO stockMovement) {
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<StockMovementResponseDTO> entity = new HttpEntity<>(stockMovement, headers);
        restTemplate.exchange("http://localhost:8080/api/stock-movement/{id}", HttpMethod.PUT, entity, Void.class);
    }
}
