package br.erp.myerp.domain.order.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class ProductClientImpl implements ProductClient{

    @Autowired
    private RestTemplate restTemplate;
}
