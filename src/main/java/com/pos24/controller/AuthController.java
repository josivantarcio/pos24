package com.pos24.controller;

import com.pos24.dto.AuthRequest;
import com.pos24.dto.AuthResponse;
import com.pos24.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    /**
     * Autentica um usuário e retorna um token JWT.
     *
     * @param request DTO contendo as credenciais do usuário
     * @return ResponseEntity com o token JWT
     */
    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
} 