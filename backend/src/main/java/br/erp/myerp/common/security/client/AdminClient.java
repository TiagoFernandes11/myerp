package br.erp.myerp.common.security.client;

import br.erp.myerp.common.security.dto.admin.AdminDTO;

public interface AdminClient {

    AdminDTO getAdmin(String username);
}
