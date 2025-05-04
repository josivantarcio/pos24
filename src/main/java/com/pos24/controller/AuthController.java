package com.pos24.controller;

import com.pos24.dto.LoginDTO;
import com.pos24.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por gerenciar as operações de autenticação.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {
    
    private final AuthService authService;
    
    /**
     * Construtor que inicializa o controlador de autenticação.
     *
     * @param authService Serviço de autenticação
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    /**
     * Autentica um usuário e retorna um token JWT.
     *
     * @param loginDTO DTO contendo as credenciais do usuário
     * @return ResponseEntity com o token JWT
     */
    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
} 