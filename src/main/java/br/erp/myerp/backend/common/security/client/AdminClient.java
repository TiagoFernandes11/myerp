package br.erp.myerp.backend.common.security.client;

import br.erp.myerp.backend.common.security.dto.AdminDTO;

public interface AdminClient {

    AdminDTO getAdmin(String username);
}
