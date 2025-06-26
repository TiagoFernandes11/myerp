package br.erp.myerp.common.security.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenValidationController {

    @GetMapping("/validate/admin")
    public ResponseEntity<?> validateTokenAdmin(Authentication authentication) {
        boolean hasRoleAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (hasRoleAdmin) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
    }

    @GetMapping("/validate/customer")
    public ResponseEntity<?> validateToken(Authentication authentication) {
        boolean hasRoleAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENT") || authority.getAuthority().equals("ROLE_ADMIN"));

        if (hasRoleAdmin) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
    }
}