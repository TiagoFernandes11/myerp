package br.erp.myerp.backend.common.security.login.controller;

import br.erp.myerp.backend.common.response.Response;
import br.erp.myerp.backend.common.security.login.dto.AuthRequestDTO;
import br.erp.myerp.backend.common.security.login.service.LoginServices;
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
    private LoginServices loginServices;

    @PostMapping
    public ResponseEntity<Response> authenticate(@RequestBody @Valid AuthRequestDTO authRequest){
        loginServices.authenticate(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Login successfull"));
    }

}
