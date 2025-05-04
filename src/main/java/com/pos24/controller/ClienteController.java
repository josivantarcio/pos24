package com.pos24.controller;

import com.pos24.dto.ClienteDTO;
import com.pos24.service.ClienteService;
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
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public class ClienteController {
    
    private final ClienteService clienteService;
    
    @PostMapping
    @Operation(summary = "Criar um novo cliente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.create(clienteDTO));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente existente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.update(id, clienteDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um cliente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter um cliente pelo ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }
    
    @GetMapping("/page")
    @Operation(summary = "Listar clientes com paginação")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAll(pageable));
    }
} 