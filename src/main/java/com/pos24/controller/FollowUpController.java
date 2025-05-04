package com.pos24.controller;

import com.pos24.dto.FollowUpDTO;
import com.pos24.service.FollowUpService;
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
 * Controlador responsável por gerenciar as operações de follow-ups.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/followups")
@Tag(name = "Follow-ups", description = "Gerenciamento de follow-ups")
public class FollowUpController {
    
    private final FollowUpService followUpService;
    
    /**
     * Construtor que inicializa o serviço de follow-ups.
     *
     * @param followUpService Serviço de follow-ups
     */
    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }
    
    /**
     * Cria um novo follow-up.
     *
     * @param followUpDTO DTO contendo os dados do novo follow-up
     * @return ResponseEntity com o follow-up criado
     */
    @PostMapping
    @Operation(summary = "Criar um novo follow-up")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FollowUpDTO> create(@Valid @RequestBody FollowUpDTO followUpDTO) {
        return ResponseEntity.ok(followUpService.create(followUpDTO));
    }
    
    /**
     * Atualiza um follow-up existente.
     *
     * @param id ID do follow-up a ser atualizado
     * @param followUpDTO DTO contendo os novos dados do follow-up
     * @return ResponseEntity com o follow-up atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um follow-up existente")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FollowUpDTO> update(@PathVariable Long id, @Valid @RequestBody FollowUpDTO followUpDTO) {
        return ResponseEntity.ok(followUpService.update(id, followUpDTO));
    }
    
    /**
     * Exclui um follow-up.
     *
     * @param id ID do follow-up a ser excluído
     * @return ResponseEntity vazio com status 204
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um follow-up")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        followUpService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtém um follow-up pelo ID.
     *
     * @param id ID do follow-up
     * @return ResponseEntity com o follow-up encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter um follow-up pelo ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FollowUpDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(followUpService.findById(id));
    }
    
    /**
     * Lista todos os follow-ups.
     *
     * @return ResponseEntity com a lista de follow-ups
     */
    @GetMapping
    @Operation(summary = "Listar todos os follow-ups")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<FollowUpDTO>> findAll() {
        return ResponseEntity.ok(followUpService.findAll());
    }
    
    /**
     * Lista follow-ups com paginação.
     *
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de follow-ups
     */
    @GetMapping("/page")
    @Operation(summary = "Listar follow-ups com paginação")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<FollowUpDTO>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(followUpService.findAll(pageable));
    }
    
    /**
     * Lista follow-ups de um chamado específico.
     *
     * @param chamadoId ID do chamado
     * @return ResponseEntity com a lista de follow-ups do chamado
     */
    @GetMapping("/chamado/{chamadoId}")
    @Operation(summary = "Listar follow-ups de um chamado")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<FollowUpDTO>> findByChamadoId(@PathVariable Long chamadoId) {
        return ResponseEntity.ok(followUpService.findByChamadoId(chamadoId));
    }
} 