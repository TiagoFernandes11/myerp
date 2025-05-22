package br.erp.myerp.client.controller;

import br.erp.myerp.client.dto.ClientCreateDTO;
import br.erp.myerp.client.dto.ClientResponseDTO;
import br.erp.myerp.client.dto.ClientUpdateDTO;
import br.erp.myerp.client.service.ClientService;
import br.erp.myerp.response.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

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
    public ResponseEntity<Response> createClient(@RequestBody @Valid ClientCreateDTO clientCreateDTO){
        clientService.create(clientCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, "Client successfully created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateDTO clientUpdateDTO){
        clientService.update(id, clientUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Client was successfully updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Client id: " + id + " was successfully deleted"));
    }
}
