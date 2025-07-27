package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.dto.stockMovement.StockMovementResponseDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemCreateDTO;
import br.erp.myerp.domain.order.dto.stockMovementItem.StockMovementItemResponseDTO;
import br.erp.myerp.domain.order.security.InternalTokenProviderForOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockMovementItemClientImpl implements StockMovementItemClient{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForOrder tokenProvider;

    @Override
    public StockMovementItemResponseDTO save(StockMovementItemCreateDTO si) {
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<StockMovementItemCreateDTO> entity = new HttpEntity<>(si, headers);
        return restTemplate
                .exchange("http://localhost:8080/api/stock-movement-item", HttpMethod.POST, entity, StockMovementItemResponseDTO.class).getBody();
    }
}
