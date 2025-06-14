package br.erp.myerp.common.security.client;

import br.erp.myerp.common.security.dto.AdminDTO;

public interface AdminClient {

    AdminDTO getAdmin(String username);
}
