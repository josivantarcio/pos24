package com.pos24.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow_ups")
@Getter
@Setter
public class FollowUp extends BaseEntity {
    
    public enum Resultado {
        RESOLVIDO,
        PENDENTE,
        FEEDBACK
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id", nullable = false)
    @NotNull(message = "Chamado é obrigatório")
    private Chamado chamado;
    
    @Column(nullable = false)
    private LocalDateTime dataEnvio;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Resultado resultado;
    
    @Column
    private Duration tempoAberturaFechamento;
    
    @PrePersist
    public void prePersist() {
        if (dataEnvio == null) {
            dataEnvio = LocalDateTime.now();
        }
    }
} 