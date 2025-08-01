package br.erp.myerp.common.security.client;

import br.erp.myerp.common.security.dto.customeraccount.CustomerAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerAccountClientImpl implements CustomerAccountClient{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CustomerAccountDTO findByEmail(String email) {
        try{
            return restTemplate.getForObject("http://localhost:8080/api/customer-account/get/{email}", CustomerAccountDTO.class, email);
        }catch (HttpClientErrorException e){
            return null;
        }
    }
}
