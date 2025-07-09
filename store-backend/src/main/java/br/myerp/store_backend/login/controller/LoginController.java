package br.myerp.store_backend.login.controller;

import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerAccountResponseDTO;
import br.myerp.store_backend.customeraccount.entity.CustomerAccount;
import br.myerp.store_backend.customeraccount.service.CustomerAccountService;
import br.myerp.store_backend.login.dto.AuthRequestDTO;
import br.myerp.store_backend.login.dto.AuthResponseDTO;
import br.myerp.store_backend.login.service.LoginServices;
import br.myerp.store_backend.security.provider.JWTTokenProvider;
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

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid AuthRequestDTO authRequest, HttpServletResponse response){
        Authentication a = loginServices.authenticate(authRequest);

        CustomerAccountResponseDTO customerAccount = customerAccountService.findByUsername(a.getName());

        String jwt = tokenProvider.generateToken(a);
        response.setHeader("Authorization", jwt);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(customerAccount.getId(), jwt));
    }

}
