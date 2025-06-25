package br.erp.myerp.domain.customeraccount.security;

import br.erp.myerp.domain.stock.dto.auth.AuthRequestDTO;
import br.erp.myerp.domain.stock.dto.auth.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InternalTokenProviderForCustomerAccount implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.internal-user.username}")
    private String username;

    @Value("${security.internal-user.password}")
    private String password;

    private String jwt;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        AuthRequestDTO authRequestDTO = new AuthRequestDTO(username, password);

        ResponseEntity<AuthResponseDTO> response = restTemplate.postForEntity(
                "http://localhost:8080/api/login",
                authRequestDTO,
                AuthResponseDTO.class
        );

        if (response.getBody() != null) {
            this.jwt = response.getBody().getToken();
        }
    }

    public String getToken() {
        return jwt;
    }
}
