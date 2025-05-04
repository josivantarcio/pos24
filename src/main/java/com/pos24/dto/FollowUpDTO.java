package com.pos24.dto;

import com.pos24.model.FollowUp.Resultado;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FollowUpDTO extends BaseDTO {
    
    @NotNull(message = "Chamado é obrigatório")
    private Long chamadoId;
    
    private LocalDateTime dataEnvio;
    private Resultado resultado;
    private Duration tempoAberturaFechamento;
} 