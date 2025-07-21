package br.myerp.manager.security.login.controller;

import br.erp.myerp.common.security.login.dto.AuthRequestDTO;
import br.erp.myerp.common.security.login.dto.AuthResponseDTO;
import br.erp.myerp.common.security.login.service.LoginServices;
import br.erp.myerp.common.security.provider.JWTTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServices loginServices;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid AuthRequestDTO authRequest, HttpServletResponse response){
        Authentication a = loginServices.authenticate(authRequest);

        String jwt = tokenProvider.generateToken(a);
        response.setHeader("Authorization", jwt);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(jwt));
    }

}
