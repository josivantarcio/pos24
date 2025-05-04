package com.pos24.controller;

import com.pos24.dto.UsuarioDTO;
import com.pos24.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar as operações de usuários.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    /**
     * Construtor que inicializa o serviço de usuários.
     *
     * @param usuarioService Serviço de usuários
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    /**
     * Cria um novo usuário no sistema.
     *
     * @param usuarioDTO DTO contendo os dados do novo usuário
     * @return ResponseEntity com o usuário criado
     */
    @PostMapping
    @Operation(summary = "Criar um novo usuário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.create(usuarioDTO));
    }
    
    /**
     * Atualiza um usuário existente.
     *
     * @param id ID do usuário a ser atualizado
     * @param usuarioDTO DTO contendo os novos dados do usuário
     * @return ResponseEntity com o usuário atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário existente")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioDTO));
    }
    
    /**
     * Exclui um usuário do sistema.
     *
     * @param id ID do usuário a ser excluído
     * @return ResponseEntity vazio com status 204
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um usuário")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtém um usuário pelo ID.
     *
     * @param id ID do usuário
     * @return ResponseEntity com o usuário encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter um usuário pelo ID")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }
    
    /**
     * Lista todos os usuários do sistema.
     *
     * @return ResponseEntity com a lista de usuários
     */
    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
    
    /**
     * Lista usuários com paginação.
     *
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de usuários
     */
    @GetMapping("/page")
    @Operation(summary = "Listar usuários com paginação")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UsuarioDTO>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.findAll(pageable));
    }
    
    /**
     * Obtém um usuário pelo username.
     *
     * @param username Username do usuário
     * @return ResponseEntity com o usuário encontrado
     */
    @GetMapping("/username/{username}")
    @Operation(summary = "Obter um usuário pelo username")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.findByUsername(username));
    }
    
    /**
     * Obtém um usuário pelo email.
     *
     * @param email Email do usuário
     * @return ResponseEntity com o usuário encontrado
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Obter um usuário pelo email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }
} 