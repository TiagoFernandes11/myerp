package br.erp.myerp.domain.stockmovement.client.stock;

import br.erp.myerp.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.domain.stockmovement.security.InternalTokenProviderForStockMovement;
import br.erp.myerp.domain.stockmovement.dto.stock.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
;

@Component
public class StockClientImpl implements StockClient{

    private static String BASE_URL = "http://localhost:8080/api/stock";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForStockMovement tokenProvider;

    @Override
    public StockDTO getById(Long stockId) {
        String token = tokenProvider.getToken();

        return restTemplate.getForObject(BASE_URL + "/{id}", StockDTO.class, stockId);
    }

    @Override
    public StockDTO getByProductId(Long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenProvider.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<StockDTO> response = restTemplate.exchange(BASE_URL + "/by-product/{productId}", HttpMethod.GET, entity, StockDTO.class, productId);

        return response.getBody();
    }

    @Override
    public void update(StockUpdateDTO stockUpdateDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenProvider.getToken());
        HttpEntity<StockUpdateDTO> entity = new HttpEntity<>(stockUpdateDTO, headers);

        restTemplate.exchange(BASE_URL, HttpMethod.PUT, entity, Void.class);
    }

}
