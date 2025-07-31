package br.myerp.store_backend.client;

import br.myerp.store_backend.dto.client.ClientCreateDTO;
import br.myerp.store_backend.dto.client.ClientResponseDTO;

public interface ClientClient {

    ClientResponseDTO create(ClientCreateDTO cient);
}
