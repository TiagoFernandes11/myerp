package br.myerp.manager.login.client;

import br.myerp.manager.login.dto.AuthRequestDTO;
import br.myerp.manager.login.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ManagerAdminClientImpl implements ManagerAdminClient{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AuthResponseDTO getAdmin(AuthRequestDTO authRequestDTO) {
        return restTemplate
                .postForEntity("http://localhost:8080/api/login", authRequestDTO, AuthResponseDTO.class, new Object()).getBody();
    }
}
