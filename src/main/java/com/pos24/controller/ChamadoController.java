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

@RestController
@RequestMapping("/api/chamados")
@RequiredArgsConstructor
@Tag(name = "Chamados", description = "Gerenciamento de chamados")
public class ChamadoController {
    
    private final ChamadoService chamadoService;
    
    @PostMapping
    @Operation(summary = "Criar um novo chamado")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        return ResponseEntity.ok(chamadoService.create(chamadoDTO));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um chamado existente")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Long id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        return ResponseEntity.ok(chamadoService.update(id, chamadoDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um chamado")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chamadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter um chamado pelo ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(chamadoService.findById(id));
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os chamados")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        return ResponseEntity.ok(chamadoService.findAll());
    }
    
    @GetMapping("/page")
    @Operation(summary = "Listar chamados com paginação")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<ChamadoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(chamadoService.findAll(pageable));
    }
    
    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar chamados de um cliente")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<ChamadoDTO>> findByClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(chamadoService.findByClienteId(clienteId));
    }
} 