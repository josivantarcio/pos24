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

/**
 * Controlador responsável por gerenciar as operações de clientes.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Gerenciamento de clientes")
public class ClienteController {
    
    private final ClienteService clienteService;
    
    /**
     * Cria um novo cliente.
     *
     * @param clienteDTO DTO contendo os dados do novo cliente
     * @return ResponseEntity com o cliente criado
     */
    @PostMapping
    @Operation(summary = "Criar um novo cliente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.create(clienteDTO));
    }
    
    /**
     * Atualiza um cliente existente.
     *
     * @param id ID do cliente a ser atualizado
     * @param clienteDTO DTO contendo os novos dados do cliente
     * @return ResponseEntity com o cliente atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente existente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.update(id, clienteDTO));
    }
    
    /**
     * Exclui um cliente.
     *
     * @param id ID do cliente a ser excluído
     * @return ResponseEntity vazio com status 204
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um cliente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtém um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return ResponseEntity com o cliente encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter um cliente pelo ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }
    
    /**
     * Lista todos os clientes.
     *
     * @return ResponseEntity com a lista de clientes
     */
    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }
    
    /**
     * Lista clientes com paginação.
     *
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de clientes
     */
    @GetMapping("/page")
    @Operation(summary = "Listar clientes com paginação")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<ClienteDTO>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAll(pageable));
    }
} 