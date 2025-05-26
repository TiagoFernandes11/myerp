package br.erp.myerp.backend.client.service;

import br.erp.myerp.backend.client.dto.ClientCreateDTO;
import br.erp.myerp.backend.client.dto.ClientResponseDTO;
import br.erp.myerp.backend.client.dto.ClientUpdateDTO;
import br.erp.myerp.backend.client.entity.Client;
import br.erp.myerp.backend.client.mapper.ClientMapper;
import br.erp.myerp.backend.client.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public List<ClientResponseDTO> findAll(){
        return clientRepository.findAllResponseDTO();
    }

    public ClientResponseDTO find(Long id){
        return clientMapper.toDto(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " was not founded")));
    }

    public ClientResponseDTO find(String cpf){
        return clientMapper.toDto(clientRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Client with cpf: " + cpf + " was not founded")));
    }

    public void create(ClientCreateDTO clientCreateDTO){
        Client client = clientMapper.toClient(clientCreateDTO);
        clientRepository.save(client);
    }

    public void update(Long id, ClientUpdateDTO clientUpdateDTO){
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " was not founded"));
        client.setId(id);
        client.setFirstName(clientUpdateDTO.getFirstName());
        client.setLastName(clientUpdateDTO.getLastName());
        client.setEmail(clientUpdateDTO.getEmail());
        client.setDdd(clientUpdateDTO.getDdd());
        client.setCellphone(clientUpdateDTO.getCellphone());
        client.setBirthday(clientUpdateDTO.getBirthday());
        client.setCpf(clientUpdateDTO.getCpf());
        clientRepository.save(client);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " not found"));
        clientRepository.delete(client);
    }
}
