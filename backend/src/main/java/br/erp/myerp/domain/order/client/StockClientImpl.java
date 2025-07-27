package br.erp.myerp.domain.order.client;

import br.erp.myerp.domain.order.entity.OrderItem;
import br.erp.myerp.domain.order.security.InternalTokenProviderForOrder;
import br.erp.myerp.domain.stock.dto.stockmovement.StockMovementCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("stockClientImplForOrder")
public class StockClientImpl implements StockClient{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForOrder tokenProvider;

    public boolean checkStock(List<OrderItem> orderItems){
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<List<OrderItem>> entity = new HttpEntity<>(orderItems, headers);

        return Boolean.TRUE.equals(restTemplate.exchange("http://localhost:8080/api/stock/check-stock", HttpMethod.POST, entity, Boolean.class).getBody());
    }
}
