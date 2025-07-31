package br.myerp.store_backend.client;

import br.myerp.store_backend.dto.product.Product;
import br.myerp.store_backend.security.config.InternalTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClientImpl implements ProductClient{

    @Autowired
    private InternalTokenProvider internalTokenProvider;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Product get(Long productId) {
        String token = internalTokenProvider.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange("http://localhost:8080/api/product/{productId}", HttpMethod.GET, httpEntity, Product.class, productId).getBody();
    }
}
