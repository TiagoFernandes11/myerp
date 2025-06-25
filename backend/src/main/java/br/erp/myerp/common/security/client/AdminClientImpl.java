package br.erp.myerp.common.security.client;

import br.erp.myerp.common.security.dto.admin.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AdminClientImpl implements AdminClient{

    @Autowired
    private RestTemplate restTemplate;

    public AdminDTO getAdmin(String username){
        return restTemplate.getForObject("http://localhost:8080/api/admin/get/{username}", AdminDTO.class, username);
    }
}
