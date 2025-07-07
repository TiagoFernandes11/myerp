package br.myerp.store_backend.client;

import br.myerp.store_backend.customeraccount.dto.client.ClientCreateDTO;
import br.myerp.store_backend.customeraccount.dto.client.ClientResponseDTO;
import org.springframework.stereotype.Component;

public interface ClientClient {

    ClientResponseDTO create(ClientCreateDTO cient);
}
