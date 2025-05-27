package br.erp.myerp.backend.cruds.client.mapper;

import br.erp.myerp.backend.cruds.client.dto.ClientCreateDTO;
import br.erp.myerp.backend.cruds.client.dto.ClientResponseDTO;
import br.erp.myerp.backend.cruds.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(ClientCreateDTO clientCreateDTO);

    ClientResponseDTO toDto(Client client);
}