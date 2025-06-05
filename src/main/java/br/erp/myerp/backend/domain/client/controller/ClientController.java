package br.erp.myerp.backend.domain.client.controller;

import br.erp.myerp.backend.domain.client.dto.ClientCreateDTO;
import br.erp.myerp.backend.domain.client.dto.ClientResponseDTO;
import br.erp.myerp.backend.domain.client.dto.ClientUpdateDTO;
import br.erp.myerp.backend.domain.client.service.ClientService;
import br.erp.myerp.backend.common.security.response.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final int PAGE_SIZE = 20;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll(@RequestParam(required = false, defaultValue = "0") int pageNum){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageNum, PAGE_SIZE).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id){
        ClientResponseDTO client = clientService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @GetMapping("/by-cpf/{cpf}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable String cpf){
        ClientResponseDTO client = clientService.find(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping()
    public ResponseEntity<String> createClient(@RequestBody @Valid ClientCreateDTO clientCreateDTO){
        clientService.create(clientCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateDTO clientUpdateDTO){
        clientService.update(id, clientUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Client was successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client id: " + id + " was successfully deleted");
    }
}
