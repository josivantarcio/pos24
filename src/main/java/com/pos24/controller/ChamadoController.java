package com.pos24.controller;

import com.pos24.dto.ChamadoDTO;
import com.pos24.service.ChamadoService;
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
 * Controlador responsável por gerenciar as operações de chamados.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/chamados")
@RequiredArgsConstructor
@Tag(name = "Chamados", description = "Gerenciamento de chamados")
public class ChamadoController {
    
    private final ChamadoService chamadoService;
    
    /**
     * Cria um novo chamado.
     *
     * @param chamadoDTO DTO contendo os dados do novo chamado
     * @return ResponseEntity com o chamado criado
     */
    @PostMapping
    @Operation(summary = "Criar um novo chamado")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        return ResponseEntity.ok(chamadoService.create(chamadoDTO));
    }
    
    /**
     * Atualiza um chamado existente.
     *
     * @param id ID do chamado a ser atualizado
     * @param chamadoDTO DTO contendo os novos dados do chamado
     * @return ResponseEntity com o chamado atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um chamado existente")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Long id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        return ResponseEntity.ok(chamadoService.update(id, chamadoDTO));
    }
    
    /**
     * Exclui um chamado.
     *
     * @param id ID do chamado a ser excluído
     * @return ResponseEntity vazio com status 204
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um chamado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chamadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtém um chamado pelo ID.
     *
     * @param id ID do chamado
     * @return ResponseEntity com o chamado encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter um chamado pelo ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(chamadoService.findById(id));
    }
    
    /**
     * Lista todos os chamados.
     *
     * @return ResponseEntity com a lista de chamados
     */
    @GetMapping
    @Operation(summary = "Listar todos os chamados")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        return ResponseEntity.ok(chamadoService.findAll());
    }
    
    /**
     * Lista chamados com paginação.
     *
     * @param pageable Configuração de paginação
     * @return ResponseEntity com a página de chamados
     */
    @GetMapping("/page")
    @Operation(summary = "Listar chamados com paginação")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<ChamadoDTO>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(chamadoService.findAll(pageable));
    }
    
    /**
     * Lista chamados de um cliente específico.
     *
     * @param clienteId ID do cliente
     * @return ResponseEntity com a lista de chamados do cliente
     */
    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar chamados de um cliente")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ChamadoDTO>> findByClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(chamadoService.findByClienteId(clienteId));
    }
} 