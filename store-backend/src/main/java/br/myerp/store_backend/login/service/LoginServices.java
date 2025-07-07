package br.myerp.store_backend.login.service;

import br.myerp.store_backend.login.dto.AuthRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication authenticate(AuthRequestDTO authRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        return authentication;
    }
}
