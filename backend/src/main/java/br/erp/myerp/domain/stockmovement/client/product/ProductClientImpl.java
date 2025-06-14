package br.erp.myerp.domain.stockmovement.client.product;

import br.erp.myerp.domain.stockmovement.security.InternalTokenProviderForStockMovement;
import br.erp.myerp.domain.stockmovement.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("productClientForStockMovement")
public class ProductClientImpl implements ProductClient{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForStockMovement tokenProvider;

    public ProductDTO getProduct(Long id){
        String token = tokenProvider.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ProductDTO> response = restTemplate.exchange(
                "http://localhost:8080/api/product/{id}",
                HttpMethod.GET,
                entity,
                ProductDTO.class,
                id
        );

        return response.getBody();
    }
}
