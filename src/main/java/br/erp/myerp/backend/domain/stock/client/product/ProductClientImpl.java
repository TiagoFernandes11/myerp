package br.erp.myerp.backend.domain.stock.client.product;

import br.erp.myerp.backend.domain.stock.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("productClientForStock")
public class ProductClientImpl implements ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    public ProductDTO getProduct(Long id){
        return restTemplate.getForObject("http://localhost:8080/api/product/{id}", ProductDTO.class, id);
    }
}
