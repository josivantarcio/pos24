package com.pos24.controller;

import com.pos24.dto.LoginDTO;
import com.pos24.dto.UsuarioDTO;
import com.pos24.model.Usuario;
import com.pos24.security.JwtTokenProvider;
import com.pos24.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioService usuarioService;
    
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.usuarioService = usuarioService;
    }
    
    /**
     * Autentica um usuário e retorna um token JWT.
     *
     * @param loginDTO DTO contendo as credenciais do usuário
     * @return ResponseEntity com o token JWT
     */
    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        
        return ResponseEntity.ok(jwt);
    }
    
    /**
     * Registra um novo usuário no sistema.
     *
     * @param usuarioDTO DTO contendo os dados do novo usuário
     * @return ResponseEntity com o usuário criado
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }
} 