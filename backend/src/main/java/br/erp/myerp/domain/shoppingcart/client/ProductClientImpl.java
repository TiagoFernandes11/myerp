package br.erp.myerp.domain.shoppingcart.client;

import br.erp.myerp.domain.order.dto.product.Product;
import br.erp.myerp.domain.order.security.InternalTokenProviderForOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClientImpl implements ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForOrder internalTokenProviderForOrder;

    public Product getByID(Long id){
        String token = internalTokenProviderForOrder.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Product> response = restTemplate.exchange("http://localhost:8080/api/product/{id}", HttpMethod.GET, httpEntity, Product.class, id);

        return response.getBody();
    }
}
