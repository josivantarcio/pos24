package com.pos24.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow_ups")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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