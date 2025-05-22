package br.erp.myerp.client.mapper;

import br.erp.myerp.client.dto.ClientCreateDTO;
import br.erp.myerp.client.dto.ClientResponseDTO;
import br.erp.myerp.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(ClientCreateDTO clientCreateDTO);

    ClientResponseDTO toDto(Client client);
}