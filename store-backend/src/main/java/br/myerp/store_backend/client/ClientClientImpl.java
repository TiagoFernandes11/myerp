package br.myerp.store_backend.client;

import br.myerp.store_backend.dto.client.ClientCreateDTO;
import br.myerp.store_backend.dto.client.ClientResponseDTO;
import br.myerp.store_backend.security.config.InternalTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Component
public class ClientClientImpl implements ClientClient{

    @Value("${url.internal.erp-back-end-url}")
    private String backendUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InternalTokenProvider internalTokenProvider;

    @Override
    public ClientResponseDTO create(ClientCreateDTO client) {
        String token = internalTokenProvider.getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        HttpEntity<ClientCreateDTO> httpEntity = new HttpEntity<>(client, httpHeaders);

        ResponseEntity<ClientResponseDTO> response = restTemplate.postForEntity(backendUrl + "/client", httpEntity, ClientResponseDTO.class);

        return response.getBody();
    }
}
