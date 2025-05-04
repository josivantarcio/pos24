package com.pos24.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ClienteDTO extends BaseDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @Email(message = "E-mail inválido")
    private String email;
    
    @Pattern(regexp = "^\\+?[1-9][0-9]{10,14}$", message = "Telefone inválido")
    private String telefone;
} 