package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovement.StockMovementCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovement.StockMovementUpdateDTO;
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
    public StockMovementResponseDTO get(Long id) {
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return restTemplate
                .exchange("http://localhost:8080/api/stock-movement/" + id, HttpMethod.GET, entity, StockMovementResponseDTO.class).getBody();
    }

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
    public StockMovementUpdateDTO update(StockMovementUpdateDTO stockMovementUpdateDTO) {
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<StockMovementUpdateDTO> entity = new HttpEntity<>(stockMovementUpdateDTO, headers);
        return restTemplate
                .exchange("http://localhost:8080/api/stock-movement/" + stockMovementUpdateDTO.getId(), HttpMethod.PUT, entity, StockMovementUpdateDTO.class).getBody();
    }
}
