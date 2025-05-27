package br.erp.myerp.backend.cruds.stockmovement.client;

import br.erp.myerp.backend.cruds.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    public Product getProduct(Long id){
        return restTemplate.getForObject("http://localhost:8080/api/product/{id}", Product.class, id);
    }
}
