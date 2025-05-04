package com.pos24.dto;

import com.pos24.model.FollowUp.Resultado;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
public class FollowUpDTO extends BaseDTO {
    
    @NotNull(message = "Chamado é obrigatório")
    private Long chamadoId;
    
    private LocalDateTime dataEnvio;
    private Resultado resultado;
    private Duration tempoAberturaFechamento;
} 