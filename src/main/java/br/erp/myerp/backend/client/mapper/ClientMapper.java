package br.erp.myerp.backend.client.mapper;

import br.erp.myerp.backend.client.dto.ClientCreateDTO;
import br.erp.myerp.backend.client.dto.ClientResponseDTO;
import br.erp.myerp.backend.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(ClientCreateDTO clientCreateDTO);

    ClientResponseDTO toDto(Client client);
}