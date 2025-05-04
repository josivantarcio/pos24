package com.pos24.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Provedor de tokens JWT para autenticação.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@Component
public class JwtTokenProvider {
    
    private final SecretKey key;
    private final long validityInMilliseconds;
    
    /**
     * Construtor que inicializa o provedor de tokens JWT.
     *
     * @param secret Chave secreta para assinar os tokens
     * @param validityInMilliseconds Tempo de validade do token em milissegundos
     */
    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long validityInMilliseconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }
    
    /**
     * Cria um novo token JWT para o usuário autenticado.
     *
     * @param authentication Objeto de autenticação contendo as informações do usuário
     * @return String contendo o token JWT
     */
    public String createToken(Authentication authentication) {
        String username = authentication.getName();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        
        return Jwts.builder()
                .setSubject(username)
                .claim("auth", authorities)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }
    
    /**
     * Valida um token JWT.
     *
     * @param token Token JWT a ser validado
     * @return true se o token for válido, false caso contrário
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Extrai o nome de usuário de um token JWT.
     *
     * @param token Token JWT
     * @return Nome de usuário extraído do token
     */
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
} 