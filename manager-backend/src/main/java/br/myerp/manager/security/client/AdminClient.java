package br.myerp.manager.security.client;


import br.myerp.manager.security.dto.admin.AdminDTO;

public interface AdminClient {

    AdminDTO getAdmin(String username);
}
