package br.erp.myerp.backend.cruds.stockmovement.client.stock;

import br.erp.myerp.backend.cruds.product.entity.Product;
import br.erp.myerp.backend.cruds.stock.dto.StockUpdateDTO;
import br.erp.myerp.backend.cruds.stock.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockClientImpl implements StockClient{

    private static String BASE_URL = "http://localhost:8080/api/stock";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Stock getById(Long stockId) {
        return restTemplate.getForObject(BASE_URL + "/{id}", Stock.class, stockId);
    }

    @Override
    public Stock getByProductId(Long productId) {
        return restTemplate.getForObject(BASE_URL, Stock.class);
    }

    @Override
    public void update(StockUpdateDTO stockUpdateDTO) {
        restTemplate.put(BASE_URL, stockUpdateDTO);
    }

}
