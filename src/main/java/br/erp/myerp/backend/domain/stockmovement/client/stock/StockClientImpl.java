package br.erp.myerp.backend.domain.stockmovement.client.stock;

import br.erp.myerp.backend.domain.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.domain.stock.entity.Stock;
import br.erp.myerp.backend.domain.stock.security.InternalTokenProviderForStockMovement;
import br.erp.myerp.backend.domain.stockmovement.dto.stock.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        return restTemplate.getForObject(BASE_URL + "/{productId}", StockDTO.class, productId);
    }

    @Override
    public void update(StockUpdateDTO stockUpdateDTO) {
        restTemplate.put(BASE_URL, stockUpdateDTO);
    }

}
