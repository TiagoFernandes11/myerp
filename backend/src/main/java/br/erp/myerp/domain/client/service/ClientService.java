package br.erp.myerp.domain.client.service;

import br.erp.myerp.domain.client.dto.ClientCreateDTO;
import br.erp.myerp.domain.client.dto.ClientResponseDTO;
import br.erp.myerp.domain.client.dto.ClientUpdateDTO;
import br.erp.myerp.domain.client.entity.Client;
import br.erp.myerp.domain.client.mapper.ClientMapper;
import br.erp.myerp.domain.client.repository.ClientRepository;
import br.erp.myerp.domain.client.specification.ClienteSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public Page<ClientResponseDTO> findAll(int pageNum, int pageSize, String filter, String value) {
        Page<Client> clients = clientRepository.findAll(ClienteSpecification.genericFilter(filter, value), PageRequest.of(pageNum, pageSize));
        return clients.map((client) -> clientMapper.toClientResponseDTO(client));
    }

    public ClientResponseDTO find(Long id) {
        return clientMapper.toDto(clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id: " + id + " was not founded")));
    }

    public ClientResponseDTO findByCpf(String cpf) {
        return clientMapper.toClientResponseDTO(clientRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Client with cpf: " + cpf + " was not founded")));
    }

    public ClientResponseDTO findByEmail(String email) {
        return clientMapper.toClientResponseDTO(clientRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Client with email: " + email + " was not founded")));
    }

    public ClientResponseDTO create(ClientCreateDTO clientCreateDTO) {
        Optional<Client> existingClient = clientRepository.findByEmail(clientCreateDTO.getEmail());
        if (existingClient.isPresent()) {
            setActive(existingClient.get().getId());
            return clientMapper.toClientResponseDTO(existingClient.get());
        }
        Client client = clientMapper.toClient(clientCreateDTO);
        client.setActive(true);
        client.setRemoved(false);

        Client savedClient = clientRepository.save(client);

        return clientMapper.toClientResponseDTO(savedClient);
    }

    public void update(Long id, ClientUpdateDTO clientUpdateDTO) {
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

    public void setActive(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not founded for id: " + id));
        client.setActive(true);
        client.setRemoved(false);
        clientRepository.save(client);
    }

    public void setInactive(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not founded for id: " + id));
        client.setActive(false);
        client.setRemoved(true);
        clientRepository.save(client);
    }
}
