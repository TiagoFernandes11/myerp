package br.erp.myerp.domain.customer.client.mapper;

import br.erp.myerp.domain.customer.client.dto.ClientCreateDTO;
import br.erp.myerp.domain.customer.client.dto.ClientResponseDTO;
import br.erp.myerp.domain.customer.client.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDTO toClientResponseDTO(Client client);

    Client toClient(ClientCreateDTO clientCreateDTO);

    ClientResponseDTO toDto(Client client);
}