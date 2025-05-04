package com.pos24.controller;

import com.pos24.dto.UsuarioDTO;
import com.pos24.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping
    @Operation(summary = "Criar um novo usuário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário existente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um usuário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter um usuário pelo ID")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
    
    @GetMapping("/page")
    @Operation(summary = "Listar usuários com paginação")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }
    
    @GetMapping("/username/{username}")
    @Operation(summary = "Obter um usuário pelo username")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.findByUsername(username));
    }
    
    @GetMapping("/email/{email}")
    @Operation(summary = "Obter um usuário pelo email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }
} 