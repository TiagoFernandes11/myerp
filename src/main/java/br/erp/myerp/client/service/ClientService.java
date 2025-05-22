package br.erp.myerp.client.service;

import br.erp.myerp.client.dto.ClientDTO;
import br.erp.myerp.client.entity.Client;
import br.erp.myerp.client.mapper.ClientMapper;
import br.erp.myerp.client.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client find(String cpf){
        Client client = clientRepository.findByCpf(cpf).orElse(null);
        if(client != null){
            return client;
        }
        throw new EntityNotFoundException("Client with cpf: " + cpf + " was not founded");
    }

    public void create(ClientDTO clientDTO){
        if (clientRepository.findByCpf(clientDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        Client client = ClientMapper.toClient(clientDTO);
        clientRepository.save(client);
    }
}
