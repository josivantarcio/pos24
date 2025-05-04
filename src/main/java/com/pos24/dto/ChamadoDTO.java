package com.pos24.dto;

import com.pos24.model.Chamado.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChamadoDTO extends BaseDTO {
    
    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;
    
    private String clienteNome;
    
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private Status status;
} 