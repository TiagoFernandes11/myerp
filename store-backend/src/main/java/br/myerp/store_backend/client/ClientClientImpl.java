package br.myerp.store_backend.client;

import br.myerp.store_backend.customeraccount.dto.client.ClientCreateDTO;
import br.myerp.store_backend.customeraccount.dto.client.ClientResponseDTO;
import br.myerp.store_backend.security.config.InternalTokenProviderForOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Component
public class ClientClientImpl implements ClientClient{

    @Value("${url.internal.erp-back-end-url}")
    private String backendUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProviderForOrder internalTokenProviderForOrder;

    @Override
    public ClientResponseDTO create(ClientCreateDTO client) {
        String token = internalTokenProviderForOrder.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        HttpEntity<ClientCreateDTO> httpEntity = new HttpEntity<>(client, httpHeaders);

        ResponseEntity<ClientResponseDTO> response = restTemplate.postForEntity(backendUrl + "/client", httpEntity, ClientResponseDTO.class);

        return response.getBody();
    }
}
