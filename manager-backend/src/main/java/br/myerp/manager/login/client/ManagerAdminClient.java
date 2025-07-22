package br.myerp.manager.login.client;


import br.myerp.manager.login.dto.AuthRequestDTO;
import br.myerp.manager.login.dto.AuthResponseDTO;

public interface ManagerAdminClient {
    AuthResponseDTO getAdmin(AuthRequestDTO authRequestDTO);
}
