package br.erp.myerp.backend.common.security.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTTokenProvider {

    private final String SECRET = "VpSgVkLh92oB8cHnM3xWyPt7sE4QvZkNyFbTmKuA6rDcQpJsLxMtUvBzHsNpZqCx";

    public String generateToken(Authentication authentication) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("My ERP")
                .subject("JWT Token")
                .claim("username", authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 30000000))
                .signWith(secretKey)
                .compact();
    }
}
