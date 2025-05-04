package com.pos24.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para autenticação de usuário.
 * Contém as credenciais necessárias para login.
 * 
 * @author POS24 Team
 * @version 1.0
 * @since 2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    
    /**
     * Nome de usuário para autenticação.
     */
    @NotBlank(message = "Username é obrigatório")
    private String username;
    
    /**
     * Senha do usuário para autenticação.
     */
    @NotBlank(message = "Password é obrigatório")
    private String password;
} 