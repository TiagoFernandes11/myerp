package br.erp.myerp.domain.client.mapper;

import br.erp.myerp.domain.client.dto.ClientCreateDTO;
import br.erp.myerp.domain.client.dto.ClientResponseDTO;
import br.erp.myerp.domain.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(ClientCreateDTO clientCreateDTO);

    ClientResponseDTO toDto(Client client);
}