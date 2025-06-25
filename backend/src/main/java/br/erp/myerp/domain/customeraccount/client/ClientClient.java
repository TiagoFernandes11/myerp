package br.erp.myerp.domain.customeraccount.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientClient {

    @Autowired
    private RestTemplate restTemplate;


}
