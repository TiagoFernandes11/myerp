package br.erp.myerp.domain.order.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("stockClientImplForOrder")
public class StockClientImpl implements StockClient{

    @Autowired
    private RestTemplate restTemplate;
}
