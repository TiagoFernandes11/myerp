package br.erp.myerp.client.controller;

import br.erp.myerp.client.dto.ClientDTO;
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
    public ResponseEntity<String> getClient(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body("Hello world, user: " + id);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createClient(@RequestBody @Valid ClientDTO clientDTO){
        clientService.create(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response(HttpStatus.CREATED, "Client successfully created"));
    }
}
