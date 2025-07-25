package br.myerp.manager.login.controller;

import br.myerp.manager.login.client.ManagerAdminClient;
import br.myerp.manager.login.dto.AuthRequestDTO;
import br.myerp.manager.login.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private ManagerAdminClient managerAdminClient;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid AuthRequestDTO authRequest){
        AuthResponseDTO authResponseDTO = managerAdminClient.getAdmin(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(authResponseDTO.getToken()));
    }

}
