package br.myerp.manager.security.client;

import br.erp.myerp.common.security.dto.customeraccount.CustomerAccountDTO;

public interface CustomerAccountClient {

    CustomerAccountDTO findByEmail(String email);
}
