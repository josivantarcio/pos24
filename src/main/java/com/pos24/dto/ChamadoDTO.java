package com.pos24.dto;

import java.time.LocalDateTime;

import com.pos24.model.Chamado.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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