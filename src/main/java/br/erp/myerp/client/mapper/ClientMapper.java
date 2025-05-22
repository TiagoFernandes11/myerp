package br.erp.myerp.client.mapper;

import br.erp.myerp.client.dto.ClientDTO;
import br.erp.myerp.client.entity.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setDdd(client.getDdd());
        clientDTO.setCellphone(client.getCellphone());
        clientDTO.setBirthday(client.getBirthday());
        clientDTO.setCpf(client.getCpf());
        return clientDTO;
    }

    public static Client toClient(ClientDTO clientDTO){
        Client client = new Client();
        client.setId(null);
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setEmail(clientDTO.getEmail());
        client.setDdd(clientDTO.getDdd());
        client.setCellphone(clientDTO.getCellphone());
        client.setBirthday(clientDTO.getBirthday());
        client.setCpf(clientDTO.getCpf());
        return client;
    }
}
