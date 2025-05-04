package com.pos24.controller;

import com.pos24.dto.FollowUpDTO;
import com.pos24.service.FollowUpService;
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
@RequestMapping("/api/followups")
@RequiredArgsConstructor
@Tag(name = "Follow-ups", description = "Gerenciamento de follow-ups")
public class FollowUpController {
    
    private final FollowUpService followUpService;
    
    @PostMapping
    @Operation(summary = "Criar um novo follow-up")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FollowUpDTO> create(@Valid @RequestBody FollowUpDTO followUpDTO) {
        return ResponseEntity.ok(followUpService.create(followUpDTO));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um follow-up existente")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FollowUpDTO> update(@PathVariable Long id, @Valid @RequestBody FollowUpDTO followUpDTO) {
        return ResponseEntity.ok(followUpService.update(id, followUpDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um follow-up")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        followUpService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter um follow-up pelo ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<FollowUpDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(followUpService.findById(id));
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os follow-ups")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<FollowUpDTO>> findAll() {
        return ResponseEntity.ok(followUpService.findAll());
    }
    
    @GetMapping("/page")
    @Operation(summary = "Listar follow-ups com paginação")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<FollowUpDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(followUpService.findAll(pageable));
    }
    
    @GetMapping("/chamado/{chamadoId}")
    @Operation(summary = "Listar follow-ups de um chamado")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<FollowUpDTO>> findByChamadoId(@PathVariable Long chamadoId) {
        return ResponseEntity.ok(followUpService.findByChamadoId(chamadoId));
    }
} 