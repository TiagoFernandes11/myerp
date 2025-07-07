package br.erp.myerp.domain.client.controller;

import br.erp.myerp.domain.client.dto.ClientCreateDTO;
import br.erp.myerp.domain.client.dto.ClientResponseDTO;
import br.erp.myerp.domain.client.dto.ClientUpdateDTO;
import br.erp.myerp.domain.client.service.ClientService;
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
    public ResponseEntity<List<ClientResponseDTO>> getAll(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                                               @RequestParam(name = "value", required = false, defaultValue = "") String value,
                                               @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageNum, PAGE_SIZE, filter, value).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id){
        ClientResponseDTO client = clientService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @GetMapping("/by-cpf/{cpf}")
    public ResponseEntity<ClientResponseDTO> getClientByCpf(@PathVariable String cpf){
        ClientResponseDTO client = clientService.findByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<ClientResponseDTO> getClientByEmail(@PathVariable String email){
        ClientResponseDTO client = clientService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody @Valid ClientCreateDTO clientCreateDTO){
        ClientResponseDTO clientResponseDTO = clientService.create(clientCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateDTO clientUpdateDTO){
        clientService.update(id, clientUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Client was successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        clientService.setInactive(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client id: " + id + " was successfully deleted");
    }
}
