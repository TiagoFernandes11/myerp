package br.erp.myerp.common.security.client;

import br.erp.myerp.common.security.dto.customeraccount.CustomerAccountDTO;

public interface CustomerAccountClient {

    CustomerAccountDTO findByUsername(String email);
}
